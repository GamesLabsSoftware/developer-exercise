package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Ore;

/**
 * MineEvent model.
 */
public class MineEvent extends PlayerEvent {
    private final Ore ore;

    /**
     * MineEvent constructor with Player and Ore context.
     *
     * @param player Player to which the MineEvent is scoped.
     * @param ore    Ore to which the MineEvent is scoped.
     */
    public MineEvent(Player player, Ore ore) {
        super(player);
        this.ore = ore;
    }

    /**
     * Returns the Ore specified in the MineEvent.
     *
     * @return Ore specified in the MineEvent.
     */
    public Ore getOre() {
        return ore;
    }
}
