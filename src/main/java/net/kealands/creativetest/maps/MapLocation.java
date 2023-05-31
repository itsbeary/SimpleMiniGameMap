package net.kealands.creativetest.maps;

import org.bukkit.Location;

import java.io.Serial;
import java.io.Serializable;

public record MapLocation(double x, double y, double z) implements Serializable {
    @Serial
    private static final long serialVersionUID = 69L;
    public Location getBukkitLocation(Location center) {
        return new Location(center.getWorld(), center.getX() - x, center.getY() - y, center.getZ() - z);
    }

}
