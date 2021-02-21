package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Item;

public class ObtainItemEvent extends PlayerEvent
{
    private final   Item    item;
    private         int     amount;

    public ObtainItemEvent(Player player, Item item, int amount)
    {
        super(player);

        this.item   = item;
        this.amount = amount;
    }

    public Item getItem     ()              { return item;      }
    public int  getAmount   ()              { return amount;    }

    public void setAmount   (int amount)    { this.amount = amount; }
}
