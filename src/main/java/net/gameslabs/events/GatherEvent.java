package net.gameslabs.events;

import net.gameslabs.api.IPlayer;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.IGatherable;

public class GatherEvent extends PlayerEvent {
    IGatherable item;

    public GatherEvent(IGatherable item, IPlayer player) {
        super(player);
        this.item = item;
    }

    public IGatherable getItem() { return this.item; }
}
