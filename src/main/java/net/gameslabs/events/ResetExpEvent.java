package net.gameslabs.events;

import net.gameslabs.api.IPlayer;
import net.gameslabs.api.PlayerEvent;

public class ResetExpEvent extends PlayerEvent {
    public ResetExpEvent(IPlayer player) {
        super(player);
    }
}
