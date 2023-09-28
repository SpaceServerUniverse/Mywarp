package space.yurisi.mywarp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import space.yurisi.mywarp.MywarpAPI;

public class MywarpCreateCommand extends MywarpBaseCommand {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if (!(sender instanceof Player player)) {
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage(getErrorMessage("名前を指定してください。"));
            return false;
        }
        if(args[0].length() > 20){
            sender.sendMessage(getErrorMessage("名前は20文字以下にしてください。"));
            return false;
        }
        boolean isPrivate = true;
        if(args.length == 2){
            if(args[1].equals("true") || args[1].equals("True") || args[1].equals("TRUE")) {
                isPrivate = false;
            }else if(args[1].equals("false") || args[1].equals("False") || args[1].equals("FALSE")) {
                isPrivate = true;
            }else{
                sender.sendMessage(getErrorMessage("公開可否はtrueかfalseで指定してください。"));
                return false;
            }
        }

        MywarpAPI.getInstance().createMywarp(player, args[0], isPrivate);
        player.sendMessage(getSuccessMessage("ワープポイント" + args[0] + "を作成しました。"));

        return true;
    }
}
