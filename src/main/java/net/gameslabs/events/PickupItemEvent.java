package net.gameslabs.events;

import net.gameslabs.api.Event;
import net.gameslabs.model.Item;

public class PickupItemEvent extends Event {
    Item item;
    int count;

    public PickupItemEvent(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() { return this.item; }
    public int getCount() { return this.count; }
}