package net.kealands.creativetest.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {


    // this listener is just to figure out where the locations are
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getInteractionPoint() == null)
            return;
        event.getPlayer().sendMessage(event.getInteractionPoint().getX() + ", " + event.getInteractionPoint().getY() + ", " + event.getInteractionPoint().getZ());
    }

}
