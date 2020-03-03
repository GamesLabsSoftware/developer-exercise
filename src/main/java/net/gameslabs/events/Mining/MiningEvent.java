package net.gameslabs.events.Mining;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Rock;

public class MiningEvent extends PlayerEvent {

    private final Rock rock;

    public MiningEvent(Player player, Rock rock) {
        super(player);
        this.rock = rock;
    }

    public Rock getRock() {
        return this.rock;
    }
}
