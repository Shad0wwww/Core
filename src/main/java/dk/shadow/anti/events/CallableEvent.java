package dk.shadow.anti.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CallableEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private boolean isCalled = false;

    public CallableEvent(boolean isAsync) {
        super(isAsync);
    }

    public CallableEvent() {
        this(false);
    }

    public CallableEvent call() {
        if (!this.isCalled) {
            Bukkit.getPluginManager().callEvent(this);
            this.isCalled = true;
        }
        return this;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}
