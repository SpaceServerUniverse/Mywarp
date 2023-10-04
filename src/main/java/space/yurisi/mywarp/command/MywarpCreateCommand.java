package space.yurisi.mywarp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import space.yurisi.mywarp.MywarpAPI;
import space.yurisi.universecore.exception.MywarpNotFoundException;
import space.yurisi.universecore.exception.UserNotFoundException;

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
            sender.sendMessage(getErrorMessage("名前は空白なしの20文字以下にしてください。"));
            return false;
        }
        if(args.length == 1) {
            sender.sendMessage(getErrorMessage("公開可否をtrueかfalseで指定してください"));
            return false;
        }
        try{
            boolean isExist = MywarpAPI.getInstance().isExistMywarpName(player, args[0]);
            if(isExist){
                sender.sendMessage(getErrorMessage("その名前のワープポイントは既に存在します。"));
                return false;
            }
        } catch (MywarpNotFoundException e) {
            // ここは何もしない
        } catch (UserNotFoundException e) {
            sender.sendMessage(getErrorMessage("ユーザーデータが存在しないようです。管理者に報告してください。 コード-MWC1"));
            return false;
        }

        boolean isPrivate;
        if(args[1].equals("true") || args[1].equals("True") || args[1].equals("TRUE")) {
            isPrivate = false;
        }else if(args[1].equals("false") || args[1].equals("False") || args[1].equals("FALSE")) {
            isPrivate = true;
        }else{
            sender.sendMessage(getErrorMessage("公開可否はtrueかfalseで指定してください。"));
            return false;
        }

        MywarpAPI.getInstance().createMywarp(player, args[0], isPrivate);
        String isPrivateString = isPrivate ? "非公開" : "公開";
        player.sendMessage(getSuccessMessage("ワープポイント" + args[0] + "を" + isPrivateString + "で作成しました。"));

        return true;
    }
}
