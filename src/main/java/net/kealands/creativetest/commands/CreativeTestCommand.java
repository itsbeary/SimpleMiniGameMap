package net.kealands.creativetest.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import net.kealands.creativetest.maps.Map;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@CommandAlias("map|creativetest")
public class CreativeTestCommand extends BaseCommand {

    // this is just testing
    private Map map;

    @Subcommand("paste")
    public void paste(Player player, String name) {
        map = new Map(name);
        map.paste(player.getLocation());
        player.sendMessage("Pasted map " + name + "!");
    }
    @Subcommand("clear")
    public void clear(Player player) {
        if(map == null) {
            player.sendMessage("There is no map to clear!");
            return;
        }
        map.clear();
        player.sendMessage("Cleared map " + map.getName() + "!");
    }
    @Subcommand("save")
    public void save(Player player, String name, int x1, int y1, int z1, int x2, int y2, int z2) {
        new Map(name, player.getLocation(), new Location(player.getWorld(), x1, y1, z1), new Location(player.getWorld(), x2, y2, z2));
        player.sendMessage("Saving map " + name + "!");
    }
}
