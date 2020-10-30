package net.gameslabs.events;

import net.gameslabs.api.Event;
import net.gameslabs.api.Player;
import net.gameslabs.model.IGatherable;

public class GatherEvent extends Event {
    IGatherable item;
    Player player;

    public GatherEvent(IGatherable item, Player player) {
        this.item = item;
        this.player = player;
    }

    public IGatherable getItem() { return this.item; }
    public Player getPlayer() { return this.player; }
}
