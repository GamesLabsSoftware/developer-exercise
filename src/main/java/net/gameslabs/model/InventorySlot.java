package net.gameslabs.model;

public class InventorySlot {
    private int maxSize;
    private Item item = null;
    private int count = 0;

    public InventorySlot(int maxSize) {
        this.maxSize = maxSize;
    }

    public InventorySlot(int maxSize, Item item, int count) {
        this.maxSize = maxSize;
        this.item = item;
        this.count = count;
    }

    public int getMaxSize() { return this.maxSize; }
    public Item getItem() { return this.item; }
    public int getCount() { return this.count; }

    public boolean tryAddingItem(Item inputItem, int quantity) {
        boolean slotIsEmpty = this.item == null;
        if (slotIsEmpty) {
            addItem(inputItem);
            return true;
        } else {
            boolean slotItemMatchesInput = this.item.equals(inputItem);
            if (slotItemMatchesInput && canHold(quantity)) {
                addItem(inputItem);
                return true;
            }
        }

        return false;
    }

    public boolean hasItem(Item item) {
        return this.item == null ? false : this.item.equals(item);
    }

    private void addItem(Item item) { addItem(item, 1); }

    private void addItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    private boolean canHold(int num) {
        int remainingCapacity = this.maxSize - this.count;
        return num <= remainingCapacity;
    }
}
