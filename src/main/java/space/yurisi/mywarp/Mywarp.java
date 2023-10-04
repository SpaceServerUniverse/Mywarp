package space.yurisi.mywarp;

import org.bukkit.plugin.java.JavaPlugin;

import space.yurisi.mywarp.connector.UniverseCoreAPIConnector;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.mywarp.command.MywarpCommandManagaer;


public final class Mywarp extends JavaPlugin {

    private UniverseCoreAPIConnector connector;

    @Override
    public void onEnable() {
        // Plugin startup logic
        DatabaseManager manager = UniverseCoreAPI.getInstance().getDatabaseManager();
        this.connector = new UniverseCoreAPIConnector(manager);
        new MywarpCommandManagaer(this);
    }

    public UniverseCoreAPIConnector getConnector(){
        return this.connector;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
