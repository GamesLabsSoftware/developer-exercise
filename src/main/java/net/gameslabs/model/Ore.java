package net.gameslabs.model;

/**
 * Enum representing Ores and related details.
 */
public enum Ore {
    COPPER(Item.ORE_COPPER, 1, 15),
    IRON(Item.ORE_IRON, 10, 15),
    TIN(Item.ORE_TIN, 1, 20);

    /**
     * Ore constructor with name.
     *
     * @param item                    Item representation of the Ore.
     * @param requiredMiningLevel     Mining level required to mine Ore.
     * @param miningExperienceGranted Mining experience granted from mining.
     */
    Ore(Item item, int requiredMiningLevel, int miningExperienceGranted) {
        this.item = item;
        this.requiredMiningLevel = requiredMiningLevel;
        this.miningExperienceGranted = miningExperienceGranted;
    }

    private final Item item;
    private final int requiredMiningLevel;
    private final int miningExperienceGranted;

    /**
     * Returns the Ore's Item representation.
     *
     * @return Ore Item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the Ore's required mining level.
     *
     * @return Ore required mining level.
     */
    public int getRequiredMiningLevel() {
        return requiredMiningLevel;
    }

    /**
     * Returns the Ore's granted mining experience.
     *
     * @return Ore mining experience.
     */
    public int getMiningExperienceGranted() {
        return miningExperienceGranted;
    }
}
