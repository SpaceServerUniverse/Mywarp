package space.yurisi.mywarp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import space.yurisi.mywarp.MywarpAPI;
import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.exception.MywarpNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;

import java.util.List;

public class MywarpVisitListCommand extends MywarpBaseCommand {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if(!(sender instanceof Player player)){
            return false;
        }
        if(args.length == 0){
            player.sendMessage(getErrorMessage("ユーザー名を入力してください。"));
            return true;
        }
        try {
            List<Mywarp> data = MywarpAPI.getInstance().showListPublicMywarpFromName(args[0]);
            // ワープポイントの一覧をフォームで表示
            // デバッグ用
            for(Mywarp mywarp : data){
                player.sendMessage(getSuccessMessage(mywarp.getName()));
            }
        } catch (UserNotFoundException e) {
            player.sendMessage(getErrorMessage("ユーザーが見つかりませんでした。"));
        } catch (MywarpNotFoundException e) {
            player.sendMessage(getErrorMessage("公開ワープポイントが見つかりませんでした。"));
        }

        return true;
    }
}