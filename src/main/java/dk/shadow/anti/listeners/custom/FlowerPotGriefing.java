package dk.shadow.anti.listeners.custom;

import dk.shadow.anti.events.FlowerPotGriefingEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FlowerPotGriefing implements Listener {


    @EventHandler
    public void onFlowerPotGriefing(FlowerPotGriefingEvent event) {
        if(event.getPlayer().isOp()) {
            event.setCancelled(true);

        }

    }
}
