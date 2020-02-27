package net.gameslabs.api;

public interface EventMethod<T extends Event> {
    /**
     * Called when an event is executed
     * @param event the target event
     */
    void onExecute(T event);
}
