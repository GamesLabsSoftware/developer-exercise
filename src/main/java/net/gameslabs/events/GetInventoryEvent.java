package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Inventory;

public class GetInventoryEvent extends PlayerEvent
{
    private Inventory inventory;

    public GetInventoryEvent(Player player)
    {
        super(player);
    }

    public Inventory getInventory() { return this.inventory; }
    
    public void setInventory(Inventory inventory)
    {
        this.inventory = inventory;
    }
}
