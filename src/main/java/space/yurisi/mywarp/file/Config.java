package space.yurisi.mywarp.file;

import org.bukkit.configuration.file.FileConfiguration;
import space.yurisi.mywarp.Mywarp;

import java.util.List;

public class Config {

    private final Mywarp main;

    private FileConfiguration config = null;

    public Config(Mywarp main){
        this.main = main;
        init();
    }

    private void init(){
        main.saveDefaultConfig();
        if (config != null) {
            main.reloadConfig();
        }
        this.config = main.getConfig();
    }

    public List<String> getDenyWorlds(){
        return this.config.getStringList("settings.deny-worlds");
    }
}
