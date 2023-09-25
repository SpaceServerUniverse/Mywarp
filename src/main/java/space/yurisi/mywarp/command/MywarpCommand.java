package space.yurisi.mywarp.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.database.repositories.MywarpRepository;
import space.yurisi.universecore.database.repositories.UserRepository;
import space.yurisi.universecore.expection.MywarpNotFoundException;
import space.yurisi.universecore.expection.UserNotFoundException;

import java.util.List;

public class MywarpCommand implements CommandExecutor{

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        MywarpRepository mywarpRepository = Bukkit.getServicesManager().getRegistration(MywarpRepository.class).getProvider();
        UserRepository userRepository = Bukkit.getServicesManager().getRegistration(UserRepository.class).getProvider();

        Long user_id = null;
        List<Mywarp> data = null;

        if(!(sender instanceof Player player)){
            sender.sendMessage("プレイヤーからのみ実行可能です。");
            return false;
        }
        String command_name = args[0];
        if(command_name.isEmpty()){
            sender.sendMessage("コマンド名を入力してください。" +
                    "使用可能なコマンドは以下の通りです。" +
                    "/mywarp create warp_name is_private" +
                    "/mywarp list" +
                    "/mywarp tp warp_name" +
                    "/mywarp edit <warp_name>" +
                    "/mywarp delete <warp_name>" +
                    "/mywarp visit <player_name>"
                    );
            return false;
        }

        switch (command_name){

            case "create":
                String warp_name = args[1];
                Boolean is_private = true;
                if(!args[2].isEmpty()){
                    is_private = Boolean.parseBoolean(args[2]);
                }
                mywarpRepository.createMywarp(player, warp_name, is_private);
                sender.sendMessage("マイワープを作成しました。");
                break;

            case "tp", "list", "edit", "delete":
                try {
                    user_id = userRepository.getPrimaryKeyFromPlayerName(player.getName());
                    data = mywarpRepository.getMywarpFromUserId(user_id);
                } catch (UserNotFoundException e){
                    sender.sendMessage("ユーザーデータが見つかりませんでした。管理者にお問い合わせください。");
                } catch (MywarpNotFoundException e){
                    sender.sendMessage("マイワープが見つかりませんでした。");
                }

                if(!command_name.equals("tp") || args[1].isEmpty()){
                    // show mywarp list in form and select warp point or edit or delete
                }else{
                    // warp name から warp point を取得
                    Mywarp mywarp = null;
                    for(Mywarp warp : data){
                        if(warp.getName().equals(args[2])){
                            mywarp = warp;
                            break;
                        }
                    }
                    if(mywarp == null){
                        sender.sendMessage("マイワープが見つかりませんでした。");
                        return false;
                    }
                    switch (command_name){
                        case "tp":
                            Location location = new Location(Bukkit.getWorld(mywarp.getWorld_name()), mywarp.getX(), mywarp.getY(), mywarp.getZ());
                            sender.sendMessage("[テレポートAI] : " + args[2] + "にワープしました。");
                            player.teleport(location);
                            break;
                        case "edit":
                            // edit warp point
                            // どうするかは考え中
                            break;
                        case "delete":
                            // delete warp point
                            try {
                                mywarpRepository.deleteMywarp(mywarp);
                                sender.sendMessage("マイワープを削除しました。");
                            } catch (MywarpNotFoundException e){
                                sender.sendMessage("マイワープが見つかりませんでした。管理者にお問い合わせください。");
                            }
                            break;
                    }
                }
                break;

            case "visit":
                if(args[1].isEmpty()){
                    sender.sendMessage("プレイヤー名を入力してください。");
                    return false;
                }
                String target_player_name = args[1];
                Long target_user_id = null;
                try {
                    target_user_id = userRepository.getPrimaryKeyFromPlayerName(target_player_name);
                    data = mywarpRepository.getPublicMywarpFromUserId(target_user_id);
                } catch (UserNotFoundException e){
                    sender.sendMessage("ユーザーが見つかりませんでした。");
                } catch (MywarpNotFoundException e){
                    sender.sendMessage("マイワープが見つかりませんでした。");
                }
                // show mywarp list in form and select warp point
                break;
            default:
                sender.sendMessage("コマンド名を間違えています。");
                return false;
        }
        return true;
    }
}
