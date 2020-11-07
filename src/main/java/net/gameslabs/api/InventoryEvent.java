package net.gameslabs.api;

/**
 * An event specific to an inventory
 */
public class InventoryEvent extends Event {
    private final String inventoryId;

    public InventoryEvent(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryId() {
        return this.inventoryId;
    }
}
