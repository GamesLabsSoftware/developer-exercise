package net.gameslabs.model;

import java.util.List;

public class Inventory {

    // I've taken some lazy liberties here, sorry :D
    private final int INVENTORY_SIZE = 36;
    private List<ItemSlot> itemSlots;

    public Inventory() {

    }

    private boolean hasItem(int id) {
        ItemSlot existing = itemSlots
                .stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);

        return existing != null;
    }

    private boolean hasItem(int id, int amount) {
        int itemCount = itemSlots
                .stream()
                .filter(i -> i.getId() == id)
                .mapToInt(ItemSlot::getAmount)
                .sum();

        return itemCount >= amount;
    }
}
