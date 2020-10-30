package net.gameslabs.events;

import net.gameslabs.api.IPlayer;
import net.gameslabs.api.PlayerEvent;

public class TakeDamageEvent extends PlayerEvent {
    private final int damageAmount;

    public TakeDamageEvent(IPlayer player, int damageAmount) {
        super(player);
        this.damageAmount = damageAmount;
    }

    public int getDamageAmount() {
        return this.damageAmount;
    }
}
