package net.gameslabs.events;

import net.gameslabs.api.Event;
import net.gameslabs.model.Item;

public class DropItemEvent extends Event {
    Item item;
    int count;

    public DropItemEvent(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    public Item getItem() { return this.item; }
    public int getCount() { return this.count; }
}
