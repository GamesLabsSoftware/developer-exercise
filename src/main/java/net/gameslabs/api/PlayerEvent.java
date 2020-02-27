package net.gameslabs.api;

/**
 * A player based event
 */
public class PlayerEvent extends Event {
    private final Player player;

    public PlayerEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
