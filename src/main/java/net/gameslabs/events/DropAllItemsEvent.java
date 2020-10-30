package net.gameslabs.events;

import net.gameslabs.api.IPlayer;
import net.gameslabs.api.PlayerEvent;

public class DropAllItemsEvent extends PlayerEvent {
    public DropAllItemsEvent(IPlayer player) {
        super(player);
    }
}
