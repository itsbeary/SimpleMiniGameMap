package net.kealands.creativetest.utils;

import net.kealands.creativetest.maps.MapLocation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;

public class MapUtil {
    public static HashMap<MapLocation, String> getMapData(Location center, Location loc1, Location loc2) {
        HashMap<MapLocation, String> data = new HashMap<>();

        for (int x = Math.min(loc1.getBlockX(), loc2.getBlockX()); x <= Math.max(loc1.getBlockX(), loc2.getBlockX()); x++)
            for (int y = Math.min(loc1.getBlockY(), loc2.getBlockY()); y <= Math.max(loc1.getBlockY(), loc2.getBlockY()); y++)
                for (int z = Math.min(loc1.getBlockZ(), loc2.getBlockZ()); z <= Math.max(loc1.getBlockZ(), loc2.getBlockZ()); z++) {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);
                    if(block.getType().equals(Material.AIR))
                        continue;
                    data.put(new MapLocation(center.getX() - block.getLocation().getX(), center.getY() - block.getLocation().getY(), center.getZ() - block.getLocation().getZ()), block.getBlockData().getAsString());
                }
        return data;
    }

}
