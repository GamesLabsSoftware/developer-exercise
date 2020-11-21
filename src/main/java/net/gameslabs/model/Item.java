package net.gameslabs.model;

// Should have basic fields for re-usability, otherwise have it as an interface and possibly defaults methods
public class Item implements IItem {
    private String id;
    private String name;

    public Item() {
        this("BASE_ITEM", "BaseItem");
    }

    public Item(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
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