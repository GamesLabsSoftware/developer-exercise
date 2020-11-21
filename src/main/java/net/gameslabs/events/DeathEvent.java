package net.gameslabs.events;

import net.gameslabs.api.IPlayer;
import net.gameslabs.api.PlayerEvent;

public class DeathEvent extends PlayerEvent {
    public DeathEvent(IPlayer player) {
        super(player);
    }
}
