package space.yurisi.mywarp;

import org.bukkit.plugin.java.JavaPlugin;

import space.yurisi.mywarp.MywarpAPI;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.mywarp.command.MywarpCommandManagaer;

import org.jetbrains.annotations.NotNull;


public final class Mywarp extends JavaPlugin {

    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.databaseManager = UniverseCoreAPI.getInstance().getDatabaseManager();
        new MywarpAPI(this.databaseManager);
        new MywarpCommandManagaer(this);
    }

    public DatabaseManager getDatabaseManager() {
        return this.databaseManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
