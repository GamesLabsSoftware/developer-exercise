package net.gameslabs.api;

/**
 * A player based event
 */
public class PlayerEvent extends Event {
    private final IPlayer player;

    public PlayerEvent(IPlayer player) {
        this.player = player;
    }

    public IPlayer getPlayer() {
        return player;
    }
}
