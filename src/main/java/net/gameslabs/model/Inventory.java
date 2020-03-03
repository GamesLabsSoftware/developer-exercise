package net.gameslabs.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    // I've taken some lazy liberties here, sorry :D
    private final int INVENTORY_SIZE = 36;
    private List<ItemSlot> itemSlots;

    public Inventory() {
        itemSlots = new ArrayList<>();
    }

    public boolean giveItem(int id, int amount) {
        // we really shouldn't be returning a result, instead we should
        // be throwing if something goes wrong, but hey ho
        if (itemSlots.size() == INVENTORY_SIZE)
            return false;

        ItemSlot existing = getFirstSlotWithItem(id);

        if (existing == null)
        {
            itemSlots.add(new ItemSlot(id, amount));
        }
        else
        {
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

    private ItemSlot getFirstSlotWithItem(int id) {
        // Not sure I want to dive in to the rabbit hole of creating stackable
        // vs non-stackable items for the sake of this exercise, or worry about
        // what happens if someone goes over a limit which will ultimately just
        // be me arbitrarily picking a number from thin air, or using 2.1b...

        return itemSlots.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }
}
