package net.kealands.creativetest;

import org.bukkit.plugin.java.JavaPlugin;

public final class CreativeTestPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        new CreativeTest().onEnable(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
