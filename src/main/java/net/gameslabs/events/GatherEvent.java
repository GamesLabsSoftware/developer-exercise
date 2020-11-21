package net.gameslabs.events;

import net.gameslabs.api.IPlayer;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.IGatherable;

public class GatherEvent extends PlayerEvent {
    // Always declare privacy/protection of fields
    private IGatherable item;

    public GatherEvent(IPlayer player, IGatherable item) {
        super(player);
        this.item = item;
    }

    public IGatherable getItem() { return this.item; }
}
