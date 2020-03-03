package net.gameslabs.events.Items;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

public class RemoveItemEvent extends PlayerEvent {
    private final int id;
    private final int amount;
    private boolean result;

    public RemoveItemEvent(Player player, int id, int amount) {
        super(player);
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public int getAmount() {
        return this.amount;
    }

    public boolean getResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
