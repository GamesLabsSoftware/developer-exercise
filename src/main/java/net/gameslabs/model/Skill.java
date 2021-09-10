package net.gameslabs.model;

/**
 * Enum representing player skills and related details.
 */
public enum Skill {
    CONSTRUCTION("Construction", 2),
    EXPLORATION("Exploration", 1),
    FIREMAKING("Firemaking", 1),
    MINING("Mining", 1);

    /**
     * Skill constructor with name and default multiplier.
     *
     * @param name
     *        Name of the Skill.
     */
    Skill(String name) {
        this.multiplier = 1;
        this.name = name;
    }

    /**
     * Skill constructor with name and multiplier.
     *
     * @param name
     *        Name of the Skill.
     * @param multiplier
     *        Skill experience multiplier.
     */
    Skill(String name, int multiplier) {
        this.multiplier = multiplier;
        this.name = name;
    }

    private int multiplier;
    private final String name;

    /**
     * Returns the Skill's experience multiplier.
     *
     * @return Skill experience multiplier.
     */
    public int getMultiplier() {
        return multiplier;
    }

    /**
     * Returns the Skill's name.
     *
     * @return Skill name.
     */
    public String getName() {
        return name;
    }
}
