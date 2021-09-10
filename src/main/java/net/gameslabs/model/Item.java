package net.gameslabs.model;

/**
 * Enum representing Items and related details.
 */
public enum Item {
    COINS("Coins", true, true),
    LOGS_STANDARD("Logs", false, true),
    LOGS_OAK("Oak Logs", false, true),
    LOGS_WILLOW("Willow Logs", false, true),
    ORE_COPPER("Copper Ore", false, true),
    ORE_IRON("Iron Ore", false, true),
    ORE_TIN("Tin Ore", false, true),
    SHRIMP_BURNT("Burnt Shrimp", false, true);

    /**
     * Item constructor with name and default values.
     *
     * @param name Name of the Item.
     */
    Item(String name) {
        this.name = name;
        this.stackable = true;
        this.tradable = true;
    }

    /**
     * Item constructor with name, stackability, and tradability values.
     *
     * @param name      Name of the Item.
     * @param stackable Boolean representing whether the Item is stackable.
     * @param tradable  Boolean representing whether the Item is tradable.
     */
    Item(String name, boolean stackable, boolean tradable) {
        this.name = name;
        this.stackable = stackable;
        this.tradable = tradable;
    }

    private final String name;
    private final Boolean stackable;
    private final Boolean tradable;

    /**
     * Returns the Item's name.
     *
     * @return Item name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Item's stackability.
     *
     * @return Item stackability.
     */
    public boolean isStackable() {
        return stackable;
    }

    /**
     * Returns the Item's tradability.
     *
     * @return Item tradability.
     */
    public boolean isTradable() {
        return tradable;
    }
}
