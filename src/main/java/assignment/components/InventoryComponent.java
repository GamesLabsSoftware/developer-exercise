package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IInventory;
import net.gameslabs.events.DeathEvent;
import net.gameslabs.events.DropItemEvent;
import net.gameslabs.events.PickupItemEvent;
import net.gameslabs.model.Helper;
import net.gameslabs.model.Inventory;
import net.gameslabs.model.InventorySlot;

import java.util.ArrayList;

public class InventoryComponent extends Component implements IInventory {
    private final String id;
    private final Inventory inventory;

    public InventoryComponent(String playerId, int numSlots, int slotSize) {
        this.id = playerId;
        this.inventory = new Inventory(numSlots, slotSize);
    }

    @Override
    public void onLoad() {
        registerEvent(DropItemEvent.class, this::onDropItem);
        registerEvent(PickupItemEvent.class, this::onPickupItem);
        registerEvent(DeathEvent.class, this::onDeath);
    }

    @Override
    public void onUnload() {
    }

    private void onDropItem(DropItemEvent event) {
        // Why do you check for null?
        // An event can never be null and by construction item should never be null neither
        // Same for count, no one will drop 0 item. Removed that check
        // And on a side note, you are possibly cancelling a null event ? NPE
        // I will comment out the code so that you can read it without doing a git blame

        // boolean isInvalidEvent = event == null || event.getItem() == null || event.getCount() <= 0;
        // Same here, Don't use == for strings
        boolean isRelevantEvent = event.getInventoryId().equals(this.id);

        /*if (isInvalidEvent) {
            event.setCancelled(true);
        } else */
        if (isRelevantEvent) {
            // The inventory component should be the class handling the dropping mechanics. the inventory should be a model,
            // not a piece of logic code
            boolean success = this.inventory.tryDropItem(event.getItem(), event.getCount());
            if (!success) {
                Helper.log("INFO: Failed to drop item.");
                event.setCancelled(true);
            }
        }
    }

    private void onPickupItem(PickupItemEvent event) {
        boolean isInvalidEvent = event == null || event.getItem() == null || event.getCount() <= 0;
        boolean isRelevantEvent = event.getInventoryId() == this.id;

        if (isInvalidEvent) {
            event.setCancelled(true);
        } else if (isRelevantEvent) {
            boolean success = this.inventory.tryPickupItem(event.getItem(), event.getCount());
            if (!success) {
                Helper.log("INFO: Failed to pickup item.");
                event.setCancelled(true);
            }
        }
    }

    private void onDeath(DeathEvent event) {
        boolean isRelevantEvent = event.getPlayer().getInventory().getId() == this.id;

        if (isRelevantEvent) {
            this.inventory.dropAllItems();
        }
    }

    @Override
    public String toString() {
        return this.inventory.toString();
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<InventorySlot> getSlots() {
        return this.inventory.getSlots();
    }

    public InventorySlot getSlot(int index) {
        return this.inventory.getSlot(index);
    }
}