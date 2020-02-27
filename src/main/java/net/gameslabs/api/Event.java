package net.gameslabs.api;

public class Event {
    private boolean cancelled;

    public Event() {
        // Nothing to do
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
