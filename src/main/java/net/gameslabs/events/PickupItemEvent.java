package net.gameslabs.events;

import net.gameslabs.api.InventoryEvent;
import net.gameslabs.model.Item;

public class PickupItemEvent extends InventoryEvent {
    Item item;
    int count;

    public PickupItemEvent(String inventoryId, Item item, int count) {
        super(inventoryId);
        this.item = item;
        this.count = count;
    }

    public Item getItem() { return this.item; }
    public int getCount() { return this.count; }
}
