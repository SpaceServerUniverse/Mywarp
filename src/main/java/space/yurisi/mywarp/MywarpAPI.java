package space.yurisi.mywarp;

import org.bukkit.entity.Player;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.universecore.database.models.Mywarp;
import space.yurisi.universecore.database.repositories.MywarpRepository;
import space.yurisi.universecore.database.repositories.UserRepository;
import space.yurisi.universecore.expection.MywarpNotFoundException;
import space.yurisi.universecore.expection.UserNotFoundException;

import java.util.List;

public class MywarpAPI extends UniverseCoreAPIConnector {

    private static MywarpAPI api;

    public MywarpAPI(DatabaseManager databaseManager) {
        super(databaseManager);
        api = this;
    }

    public Mywarp getMywarpFromName(Player player, String warp_name) throws UserNotFoundException, MywarpNotFoundException{
        return this.baseGetMywarpFromName(player, warp_name);
    }

    public Boolean isExistMywarpName(Player player, String warp_name) throws UserNotFoundException, MywarpNotFoundException{
        return this.baseisExistMywarpName(player, warp_name);
    }

    public void createMywarp(Player player, String warp_name, Boolean is_private){
        this.baseCreateMywarp(player, warp_name, is_private);
    }

    public void deleteMywarp(Mywarp mywarp) throws MywarpNotFoundException{
        this.baseDeleteMywarp(mywarp);
    }

    public List<Mywarp> showListMywarp(Player player) throws MywarpNotFoundException, UserNotFoundException{
        return this.baseGetMywarpList(player);
    }

    public List<Mywarp> showListPublicMywarpFromName(String target_user_name) throws MywarpNotFoundException, UserNotFoundException{
        return this.baseGetPublicMywarpListFromName(target_user_name);
    }

    public void teleportMywarp(Player player, Mywarp mywarp) throws MywarpNotFoundException{
        this.baseTeleportMywarp(player, mywarp);
    }

    public static MywarpAPI getInstance() {
        return api;
    }
}
