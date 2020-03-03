package net.gameslabs.model.Items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    // I've taken some lazy liberties here, sorry :D
    private final int INVENTORY_SIZE = 36;
    
    // How do we best manage whether a slot is empty?
    // Official runescape client uses -1 for id when a slot is empty, but never
    // liked this approach, feels lazy, but null items aren't really any better
    private List<ItemSlot> itemSlots;

    public Inventory() {
        itemSlots = new ArrayList<>();
    }

    public boolean giveItem(int id, int amount) {
        // we really shouldn't be returning a result for these, instead we should
        // be throwing an exception if something goes wrong, but hey ho
        if (itemSlots.size() == INVENTORY_SIZE)
            return false;

        if (amount <= 0)
            return false;

        ItemSlot existing = getFirstSlotWithItem(id);

        if (existing == null) {
            itemSlots.add(new ItemSlot(id, amount));
        }
        else {
            existing.setAmount(existing.getAmount() + amount);
        }

        return true;
    }

    public boolean hasItem(int id, int amount) {
        int itemCount = itemSlots
                .stream()
                .filter(i -> i.getId() == id)
                .mapToInt(ItemSlot::getAmount)
                .sum();

        return itemCount >= amount;
    }

    public boolean removeItem(int id, int amount) {
        if (amount <= 0)
            return false;

        if (!hasItem(id, amount))
            return false;

        ItemSlot existing = getFirstSlotWithItem(id);

        if (existing == null) {
            return false;
        }
        else {
            existing.setAmount(existing.getAmount() - amount);
        }

        return true;
    }

    private ItemSlot getFirstSlotWithItem(int id) {
        // Not sure I want to dive in to the rabbit hole of creating stackable
        // vs non-stackable items for the sake of this exercise, or worry about
        // what happens if someone goes over a limit which will ultimately just
        // be me arbitrarily picking a number from thin air, or using 2.1b...

        return itemSlots.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
}
