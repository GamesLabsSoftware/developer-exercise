package net.gameslabs.model;

public class InventorySlot {
    private int maxSize;
    private Item item;
    private int count;

    // Use one constructor
    public InventorySlot(int maxSize) {
        this(maxSize, null, 0);
    }

    public InventorySlot(int maxSize, Item item, int count) {
        this.maxSize = maxSize;
        this.item = item;
        this.count = count;
    }

    @Override
    public String toString() {
        return isEmpty()
                ? "Empty"
                : "'" + this.getItem().getName() + "' x" + this.getCount();
    }

    public int getMaxSize() { return this.maxSize; }
    public Item getItem() { return this.item; }
    public int getCount() { return this.count; }

    // This shouldn't me in a model class
    public int tryAddingItem(Item inputItem, int count) {
        int numAdded = 0;

        if (!canHold(count)) {
            return numAdded;
        }

        boolean isEmpty = this.item == null;
        if (isEmpty) {
            setItem(inputItem, count);
            numAdded = count;
        } else {
            boolean slotItemMatchesInput = this.item.equals(inputItem);
            if (slotItemMatchesInput) {
                addToItem(count);
                numAdded = count;
            }
        }

        return numAdded;
    }

    public boolean hasItem(Item item) {
        // expression can be simplified..
        return this.item != null && this.item.equals(item);
    }

    public void drop(int num) {
        this.count -= num;
        if (this.count <= 0) {
            this.item = null;
            this.count = 0;
        }
    }

    public boolean isEmpty() { return this.count == 0 || this.item == null; }

    public int remainingCapacity() { return this.maxSize - this.count; }

    private void addToItem(int count) {
        this.count += count;
    }

    private void setItem(Item item, int count) {
        this.item = item;
        this.count = count;
    }

    private boolean canHold(int num) {
        return num <= remainingCapacity();
    }
}
