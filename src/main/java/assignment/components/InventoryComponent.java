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

    public InventorySlot getSlot(int index) { return this.inventory.getSlot(index); }

    private void onDropItem(DropItemEvent event) {
        boolean isInvalidEvent = event == null || event.getItem() == null || event.getCount() <= 0;
        if (isInvalidEvent) {
            event.setCancelled(true);
        } else if (event.getInventoryId() == this.id) {
            boolean success = this.inventory.tryDropItem(event.getItem(), event.getCount());
            if (!success) {
                Helper.log("INFO: Failed to drop item.");
                event.setCancelled(true);
            }
        }
    }

    private void onPickupItem(PickupItemEvent event) {
        boolean isInvalidEvent = event == null || event.getItem() == null || event.getCount() <= 0;
        if (isInvalidEvent) {
            event.setCancelled(true);
        } else if (event.getInventoryId() == this.id) {
            boolean success = this.inventory.tryPickupItem(event.getItem(), event.getCount());
            if (!success) {
                Helper.log("INFO: Failed to pickup item.");
                event.setCancelled(true);
            }
        }
    }

    private void onDeath(DeathEvent event) {
        if (event.getPlayer().getInventory().getId() == this.id) {
            this.inventory.dropAllItems();
        }
    }
}