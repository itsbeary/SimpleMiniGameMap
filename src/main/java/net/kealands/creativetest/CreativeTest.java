package net.kealands.creativetest;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.kealands.creativetest.commands.CreativeTestCommand;
import net.kealands.creativetest.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CreativeTest {

    @Getter
    private static JavaPlugin plugin;
    @Getter
    private static CreativeTest instance;

    public void onEnable(JavaPlugin plugin) {
        CreativeTest.plugin = plugin;
        CreativeTest.instance = this;
        PaperCommandManager commandManager = new PaperCommandManager(plugin);
        commandManager.registerCommand(new CreativeTestCommand());
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), plugin);
    }
}
