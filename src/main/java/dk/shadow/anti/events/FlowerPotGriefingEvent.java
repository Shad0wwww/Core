package dk.shadow.anti.events;

import org.bukkit.entity.Player;

public class FlowerPotGriefingEvent extends CancellableEvent {

    protected Player player;

    public FlowerPotGriefingEvent(Player player, boolean isCancelled) {
        this.player = player;
        setCancelled(isCancelled);
    }

    public Player getPlayer() {
        return this.player;
    }
    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean cancel) {

    }
}
