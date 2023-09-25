package space.yurisi.mywarp;

import org.bukkit.plugin.java.JavaPlugin;

import space.yurisi.mywarp.command.MywarpCommand;


public final class Mywarp extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        this.getCommand("mywarp").setExecutor(new MywarpCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
