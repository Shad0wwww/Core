package dk.shadow.anti.events;

import org.bukkit.event.Cancellable;

public class CancellableEvent extends CallableEvent implements Cancellable {
    private boolean isCancelled = false;

    public CancellableEvent(boolean isAsync) {
        super(isAsync);
    }

    public CancellableEvent() {
        this(false);
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }
}
