package net.gameslabs.model;

import java.util.EnumMap;
import java.util.Map;

/**
 * PlayerInventory model. For the sake of simplicity, we're going to ignore
 * inventory grid size/layout.
 */
public class PlayerInventory {
    private Map<Item, Integer> inventory;

    /**
     * PlayerInventory default constructor.
     */
    public PlayerInventory() {
        inventory = new EnumMap<>(Item.class);
    }

    /**
     * Returns a Map representing the PlayerInventory items and their quantities.
     *
     * @return Map representing the PlayerInventory items and their quantities.
     */
    public Map<Item, Integer> getInventory() {
        return inventory;
    }

    /**
     * Add an Item to the PlayerInventory with a default quantity of 1.
     *
     * @param item Item to be added to the PlayerInventory.
     */
    public void addItem(Item item) {
        addItem(item, 1);
    }

    /**
     * Add a specified quantity of an Item to the PlayerInventory.
     *
     * @param item     Item to be added to the PlayerInventory.
     * @param quantity Quantity of the Item to be added to the PlayerInventory.
     */
    public void addItem(Item item, int quantity) {
        if (contains(item)) {
            inventory.replace(item, getQuantity(item) + quantity);
        } else {
            inventory.put(item, quantity);
        }
    }

    /**
     * Returns a boolean representing whether the given Item is contained within the PlayerInventory.
     *
     * @param item Item to be searched.
     * @return Boolean representing whether the given Item is contained within the PlayerInventory.
     */
    public boolean contains(Item item) {
        return inventory.containsKey(item);
    }

    /**
     * Returns the quantity of a specified Item, or 0 if the Item is not contained within the PlayerInventory.
     *
     * @param item Item to be quantified.
     * @return Quantity of the specified Item.
     */
    public int getQuantity(Item item) {
        return contains(item) ? inventory.get(item) : 0;
    }

    /**
     * Removes an Item from a PlayerInventory regardless of the Item's current quantity.
     *
     * @param item Item to be removed from the PlayerInventory.
     */
    public void removeItem(Item item) {
        if (contains(item))
            inventory.remove(item);
    }

    /**
     * Removes a quantity of an Item from a PlayerInventory.
     *
     * @param item     Item to be removed from the PlayerInventory.
     * @param quantity Quantity to be removed from the PlayerInventory.
     */
    public void removeItem(Item item, int quantity) {
        if (contains(item)) {
            int newQuantity = Math.max(inventory.get(item) - quantity, 0);
            if (newQuantity < 1)
                removeItem(item);
            else
                inventory.replace(item, newQuantity);
        }
    }
}
