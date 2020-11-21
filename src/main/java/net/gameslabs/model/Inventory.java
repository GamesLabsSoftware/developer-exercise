package net.gameslabs.model;

import java.util.ArrayList;
import java.util.List;

// This is supposed to be a MODEL class, it should have 0 logic. model package = business classes
public class Inventory {
    private final int numSlots;
    private final int slotSize;
    // Same here, never use the implementation but the interface and all followings ArrayList types
    private List<InventorySlot> slots;

    public Inventory(int numSlots, int slotSize) {
        this.numSlots = numSlots;
        this.slotSize = slotSize;
        this.slots = createEmptySlots();
    }

    private List<InventorySlot> createEmptySlots() {
        List<InventorySlot> emptySlots = new ArrayList<>();
        for (int i = 0; i < this.numSlots; i++) {
            emptySlots.add(new InventorySlot(this.slotSize));
        }
        return emptySlots;
    }

    @Override
    public String toString() {
        String out = "\r\nInventory (Size: " + this.slots.size() + ")\r\n";

        for (int i = 0; i < this.slots.size(); i++) {
            out += "Slot " + i + ": " + this.slots.get(i) + "\r\n";
        }

        return out;
    }

    public List<InventorySlot> getSlots() {
        return this.slots;
    }

    public InventorySlot getSlot(int index) {
        return this.slots.get(index);
    }

    public void dropAllItems() {
        this.slots = createEmptySlots();
    }

    // Should be put in the component instead
    public boolean tryPickupItem(Item item, int numToAdd) {
        while (numToAdd > 0) {
            int numAdded = tryAddingItem(item, numToAdd);

            if (numAdded == 0) {
                //Failed to add for some reason
                return false;
            } else {
                numToAdd -= numAdded;
            }
        }

        return true;
    }

    // Should be put in the component instead
    public boolean tryDropItem(Item item, int numToDrop) {
        while (numToDrop > 0) {
            int numDropped = tryDroppingItem(item, numToDrop);

            if (numDropped == 0) {
                //Failed to drop for some reason
                return false;
            } else {
                numToDrop -= numDropped;
            }
        }

        return true;
    }

    // Should be put in the component instead. We should have an addItem and that's it
    private int tryAddingItem(Item item, int numToAdd) {
        // No need to assign it here if you don't use it here
        //int numAdded = 0;

        // <!> A method should only have one or two returns max for better readability

        if (numToAdd <= 0) {
            Helper.log("Can't add that many to inventory!");
            // Don't return an assignement
            return 0;
        }

        InventorySlot slot = tryGetCompatibleSlot(item);
        if (slot == null) {
            return 0;
        }

        int numAdded; // put it here
        if (numToAdd > slot.remainingCapacity()) {
            numAdded = slot.tryAddingItem(item, slot.remainingCapacity());
        } else {
            numAdded = slot.tryAddingItem(item, numToAdd);
        }

        return numAdded;
    }

    // Should be put in the component instead
    public int tryDroppingItem(Item item, int numToDrop) {
        // Same thing here with numDropped

        // <!> A method should only have one or two returns max for better readability

        if (numToDrop <= 0) {
            Helper.log("Can't drop that many from inventory!");
            return 0;
        }

        InventorySlot slot = tryGetSlotContainingItem(item);
        if (slot == null) {
            return 0;
        }

        int numDropped;
        if (numToDrop > slot.getCount()) {
            numDropped = slot.getCount();
            slot.drop(numDropped);
        } else {
            numDropped = numToDrop;
            slot.drop(numDropped);
        }

        return numDropped;
    }

    // Should be put in the component instead
    // + never use null really. Use optionals instead. Null shouldn't be used anywhere
    private InventorySlot tryGetSlotContainingItem(Item item) {
        // You never use the iteration variable, use a foreach
        for (InventorySlot slot : slots) {
            if (slot.hasItem(item)) {
                return slot;
            }
        }

        return null;
    }

    // Should be put in the component instead
    // + never use null really. Use optionals instead. Null shouldn't be used anywhere
    private InventorySlot tryGetCompatibleSlot(Item item) {
        //Check for a non-full slot matching the item

        // You never use the iteration variable, use a foreach
        for (InventorySlot slot : slots) {
            if (!slot.isEmpty() && slot.remainingCapacity() > 0 && slot.getItem().equals(item)) {
                return slot;
            }
        }

        //Check for an empty slot
        // You never use the iteration variable, use a foreach
        for (InventorySlot slot : slots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }

        return null;
    }
}
