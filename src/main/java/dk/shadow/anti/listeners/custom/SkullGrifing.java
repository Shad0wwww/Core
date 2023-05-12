package dk.shadow.anti.listeners.custom;

import dk.shadow.anti.events.SkullGrifingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SkullGrifing implements Listener {



    @EventHandler
    public void onSkullGrif(SkullGrifingEvent event) {
        if(event.getPlayer().isOp()) {
            event.setCancelled(true);

        }
    }
}
