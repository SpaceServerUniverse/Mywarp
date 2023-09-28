package space.yurisi.mywarp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import org.jetbrains.annotations.NotNull;

public class MywarpHelpCommand  extends MywarpBaseCommand{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        if(!(sender instanceof Player player)){
            return false;
        }

        if(args.length != 0){
            return false;
        }

        sender.sendMessage(getSuccessMessage("Mywarp Help"));
        sender.sendMessage(getSuccessMessage("/mywarp : このヘルプを表示します。<>内は必須で、()内は必須ではありません"));
        sender.sendMessage(getSuccessMessage("/mwlist : ワープポイントの一覧を表示し、選択してテレポートや削除ができます"));
        sender.sendMessage(getSuccessMessage("/mwadd <ワープ名> (公開可否:true, false) : ワープポイントを追加します。デフォルトで非公開です"));
        sender.sendMessage(getSuccessMessage("/mwdel <ワープ名> : 指定したワープポイントを削除します"));
        sender.sendMessage(getSuccessMessage("/mwtp <ワープ名> : 指定したワープポイントにテレポートします"));
        sender.sendMessage(getSuccessMessage("/mwvisit <相手の名前> : 指定したプレイヤーの公開ワープポイントを表示してテレポートできます"));

        return true;
    }
}
