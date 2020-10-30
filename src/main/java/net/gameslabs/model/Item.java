package net.gameslabs.model;

public class Item implements IItem {
    @Override
    public String getId() {
        return "BASE_ITEM";
    }

    @Override
    public String getName() {
        return "BaseItem";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Item)) {
            return false;
        }

        Item item = (Item) obj;
        return item.getId() == this.getId();
    }
}