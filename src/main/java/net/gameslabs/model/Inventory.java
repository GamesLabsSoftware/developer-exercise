package net.gameslabs.model;

import java.util.ArrayList;

public class Inventory {
    private final int numSlots;
    private final int slotSize;
    private ArrayList<InventorySlot> slots;

    public Inventory(int numSlots, int slotSize) {
        this.numSlots = numSlots;
        this.slotSize = slotSize;
        this.slots = createEmptySlots();
    }

    private ArrayList<InventorySlot> createEmptySlots() {
        ArrayList<InventorySlot> emptySlots = new ArrayList<InventorySlot>();
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

    public ArrayList<InventorySlot> getSlots() {
        return this.slots;
    }

    public InventorySlot getSlot(int index) {
        return this.slots.get(index);
    }

    public void dropAllItems() {
        this.slots = createEmptySlots();
    }

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

    private int tryAddingItem(Item item, int numToAdd) {
        int numAdded = 0;

        if (numToAdd <= 0) {
            Helper.log("Can't add that many to inventory!");
            return numAdded = 0;
        }

        InventorySlot slot = tryGetCompatibleSlot(item);
        if (slot == null) {
            return numAdded = 0;
        }

        if (numToAdd > slot.remainingCapacity()) {
            numAdded = slot.tryAddingItem(item, slot.remainingCapacity());
        } else {
            numAdded = slot.tryAddingItem(item, numToAdd);
        }

        return numAdded;
    }

    public int tryDroppingItem(Item item, int numToDrop) {
        int numDropped = 0;

        if (numToDrop <= 0) {
            Helper.log("Can't drop that many from inventory!");
            return numDropped = 0;
        }

        InventorySlot slot = tryGetSlotContainingItem(item);
        if (slot == null) {
            return numDropped = 0;
        }

        if (numToDrop > slot.getCount()) {
            numDropped = slot.getCount();
            slot.drop(numDropped);
        } else {
            numDropped = numToDrop;
            slot.drop(numDropped);
        }

        return numDropped;
    }

    private InventorySlot tryGetSlotContainingItem(Item item) {
        for (int i = 0; i < slots.size(); i++) {
            InventorySlot slot = slots.get(i);

            if (slot.hasItem(item)) {
                return slot;
            }
        }

        return null;
    }

    private InventorySlot tryGetCompatibleSlot(Item item) {
        //Check for a non-full slot matching the item
        for (int i = 0; i < slots.size(); i++) {
            InventorySlot slot = slots.get(i);
            if (!slot.isEmpty() && slot.remainingCapacity() > 0 && slot.getItem().equals(item)) {
                return slot;
            }
        }

        //Check for an empty slot
        for (int i = 0; i < slots.size(); i++) {
            InventorySlot slot = slots.get(i);
            if (slot.isEmpty()) {
                return slot;
            }
        }

        return null;
    }
}
