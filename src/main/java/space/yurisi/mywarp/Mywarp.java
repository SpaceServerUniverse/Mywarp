package space.yurisi.mywarp;

import org.bukkit.plugin.java.JavaPlugin;

import space.yurisi.mywarp.connector.UniverseCoreAPIConnector;
import space.yurisi.mywarp.file.Config;
import space.yurisi.universecore.UniverseCoreAPI;
import space.yurisi.universecore.database.DatabaseManager;
import space.yurisi.mywarp.command.MywarpCommandManagaer;


public final class Mywarp extends JavaPlugin {

    private UniverseCoreAPIConnector connector;

    private Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        DatabaseManager manager = UniverseCoreAPI.getInstance().getDatabaseManager();
        this.config = new Config(this);
        this.connector = new UniverseCoreAPIConnector(manager, this.config);
        new MywarpCommandManagaer(this);
    }

    public UniverseCoreAPIConnector getConnector(){
        return this.connector;
    }

    public Config getPluginConfig(){
        return this.config;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
