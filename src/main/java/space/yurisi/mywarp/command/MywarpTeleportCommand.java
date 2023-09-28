package space.yurisi.mywarp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import space.yurisi.mywarp.MywarpAPI;
import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.expection.MywarpNotFoundException;
import space.yurisi.universecore.expection.UserNotFoundException;

public class MywarpTeleportCommand extends MywarpBaseCommand {

        @Override
        public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
            if (!(sender instanceof Player player)) {
                return false;
            }
            try {
                Mywarp mywarp = MywarpAPI.getInstance().getMywarpFromName(player, args[0]);
                MywarpAPI.getInstance().teleportMywarp(player, mywarp);
                player.sendMessage(getSuccessMessage(args[0] + " にワープしました。"));
            } catch (UserNotFoundException e) {
                player.sendMessage(getErrorMessage("ユーザーデータが存在しないようです。管理者に報告してください。 コード-MWT1"));
            } catch (MywarpNotFoundException e) {
                player.sendMessage(getErrorMessage("ワープポイントが見つかりませんでした。"));
            }

            return true;
        }
}
