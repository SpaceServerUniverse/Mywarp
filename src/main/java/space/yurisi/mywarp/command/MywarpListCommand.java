package space.yurisi.mywarp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import space.yurisi.mywarp.connector.UniverseCoreAPIConnector;
import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.exception.MywarpNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;

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
                //下はデバッグ用
                //TODO ページング
                for(Mywarp mywarp : connector.getMywarpList(player)){
                    player.sendMessage(getSuccessMessage(mywarp.getName()));
                }
            } catch (UserNotFoundException e) {
                sender.sendMessage(getErrorMessage("ユーザーデータが存在しないようです。管理者に報告してください。 コード-UES1"));
            } catch (MywarpNotFoundException e) {
                sender.sendMessage(getErrorMessage("ワープポイントが見つかりませんでした。"));
            }
            return true;
        }
}
