package space.yurisi.mywarp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import space.yurisi.mywarp.MywarpAPI;
import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.exception.MywarpNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;

public class MywarpListCommand extends MywarpBaseCommand {

        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
            if (!(sender instanceof Player player)) {
                return false;
            }
            try {
                //formを表示してそこからtpできるようにする
                //下はデバッグ用
                for(Mywarp mywarp : MywarpAPI.getInstance().showListMywarp(player)){
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
