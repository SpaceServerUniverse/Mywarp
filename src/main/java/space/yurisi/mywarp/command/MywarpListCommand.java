package space.yurisi.mywarp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import space.yurisi.mywarp.connector.UniverseCoreAPIConnector;
import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.exception.MywarpNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;

import java.util.List;

public class MywarpListCommand extends MywarpBaseCommand {

    public MywarpListCommand(UniverseCoreAPIConnector connector) {
        super(connector);
    }

    @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
            if (!(sender instanceof Player player)) {
                return false;
            }

            try {
                //formを表示してそこからtpできるようにする
                List<Mywarp> mywarpList = connector.getMywarpList(player);
                int mwpage = (mywarpList.size() / 5) + 1;
                if(mywarpList.size() % 5 == 0){
                    mwpage--;
                }
                int page = 0;
                if(args.length == 0){
                    if(mwpage > 1){
                        player.sendMessage(getSuccessMessage("/mwlist ページ番号(§b1~" + mwpage + "§2)でページを指定できます。"));
                    }
                }else {
                    try {
                        page = Integer.parseInt(args[0]) - 1;
                    }catch (NumberFormatException e){
                        player.sendMessage(getErrorMessage("ページ番号は数字で指定してください。"));
                        return true;
                    }
                    if(page < 0){
                        player.sendMessage(getErrorMessage("ページ番号は1以上で指定してください。"));
                        return true;
                    }
                    if (page >= mwpage) {
                        player.sendMessage(getErrorMessage("ページが存在しません。§b" + mwpage + "§2ページ目までしかありません。"));
                        return true;
                    }
                }
                player.sendMessage("§a公開状態 | ワールド | ワープポイント名");
                for(int i = 0; i < 5; i++){
                    i = i + (page * 5);
                    if(mywarpList.size() <= i){
                        break;
                    }
                    Mywarp mywarp = mywarpList.get(i);
                    String mwprivate = "　公開　";
                    if(mywarp.getIs_private()) {
                        mwprivate = "非公開　";
                    }
                    player.sendMessage(mwprivate + " | " + mywarp.getWorld_name() + " | §6" + mywarp.getName());
                }
                player.sendMessage(getSuccessMessage("ワープポイント一覧 §b" + (page + 1) + "/" + mwpage + "§2ページ目を表示中"));

            } catch (UserNotFoundException e) {
                sender.sendMessage(getErrorMessage("ユーザーデータが存在しないようです。管理者に報告してください。 コード-UES1"));
            } catch (MywarpNotFoundException e) {
                sender.sendMessage(getErrorMessage("ワープポイントが見つかりませんでした。"));
            }
            return true;
        }
}
