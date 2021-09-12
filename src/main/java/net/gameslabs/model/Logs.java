package net.gameslabs.model;

/**
 * Enum representing Ores and related details.
 */
public enum Logs {
    STANDARD(Item.LOGS_STANDARD, 1, 10, 1),
    OAK(Item.LOGS_OAK, 10, 15, 2),
    WILLOW(Item.LOGS_WILLOW, 20, 20, 3);

    /**
     * Ore constructor with name.
     *
     * @param item                        Item representation of the Logs.
     * @param requiredFiremakingLevel     Firemaking level required to burn Logs.
     * @param firemakingExperienceGranted Firemaking experience granted from burning.
     * @param burnDurationMultiplier      Burn duration multiplier.
     */
    Logs(Item item, int requiredFiremakingLevel, int firemakingExperienceGranted, int burnDurationMultiplier) {
        this.item = item;
        this.requiredFiremakingLevel = requiredFiremakingLevel;
        this.firemakingExperienceGranted = firemakingExperienceGranted;
        this.burnDurationMultiplier = burnDurationMultiplier;
    }

    private final Item item;
    private final int requiredFiremakingLevel;
    private final int firemakingExperienceGranted;
    private final int burnDurationMultiplier;

    /**
     * Returns the Logs' Item representation.
     *
     * @return Logs Item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the Logs' required firemaking level.
     *
     * @return Logs required firemaking level.
     */
    public int getRequiredFiremakingLevel() {
        return requiredFiremakingLevel;
    }

    /**
     * Returns the Logs' granted firemaking experience.
     *
     * @return Logs firemaking experience.
     */
    public int getFiremakingExperienceGranted() {
        return firemakingExperienceGranted;
    }

    /**
     * Returns the Logs' burn duration multiplier.
     *
     * @return Logs burn duration multiplier.
     */
    public int getBurnDurationMultiplier() {
        return burnDurationMultiplier;
    }
}
