package net.kealands.creativetest.maps;

import lombok.Getter;
import net.kealands.creativetest.CreativeTest;
import net.kealands.creativetest.CreativeTestPlugin;
import net.kealands.creativetest.utils.MapUtil;
import net.kealands.creativetest.utils.PastedBlock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

@Getter
public class Map implements Serializable {

    @Serial
    private static final long serialVersionUID = 69L;
    private final String name;

    private HashMap<MapLocation, String> data = new HashMap<>();
    private final HashSet<Location> tempLocations = new HashSet<>();

    public Map(String name, Location copyLocation, Location location1, Location location2) {
        this.name = name;
        this.data = MapUtil.getMapData(copyLocation, location1, location2);
        String fileName = CreativeTest.getPlugin().getDataFolder() + "/maps/" + name + ".keamap";
        new File(fileName).getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(fileName);
             BukkitObjectOutputStream oos = new BukkitObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Map(String name) {
        this.name = name;
        Map map;
        try (BufferedInputStream fos = new BufferedInputStream(new FileInputStream(CreativeTest.getPlugin().getDataFolder() + "/maps/" + name + ".keamap"))) {
            BukkitObjectInputStream oos = new BukkitObjectInputStream(fos);
            map = (Map) oos.readObject();
        } catch (Exception e) {
            Bukkit.getLogger().warning("Unable to load map " + name + "!");
            return;
        }
        this.data = map.getData();
    }

    public void clear() {
        if(tempLocations.isEmpty())
            return;
        for(Location loc : tempLocations) {
            PastedBlock.BlockQueue.getQueue(loc.getWorld()).add(new PastedBlock(loc.getX(), loc.getY(), loc.getZ(), new Random().nextInt(999999), Material.AIR.createBlockData()));
        }
    }

    public void paste(Location pasteLocation) {
        long systemTime = System.currentTimeMillis();
        for(java.util.Map.Entry<MapLocation, String> entry : data.entrySet()) {
            PastedBlock.BlockQueue.getQueue(pasteLocation.getWorld()).add(new PastedBlock(entry.getKey().getBukkitLocation(pasteLocation).getX(), entry.getKey().getBukkitLocation(pasteLocation).getY(), entry.getKey().getBukkitLocation(pasteLocation).getZ(), new Random().nextInt(999999), Bukkit.createBlockData(entry.getValue())));
            tempLocations.add(entry.getKey().getBukkitLocation(pasteLocation));
        }
        Bukkit.broadcastMessage("Finish task in " + (System.currentTimeMillis() - systemTime) + " ms!");
    }

}
