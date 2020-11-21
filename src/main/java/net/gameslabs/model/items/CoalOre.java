package net.gameslabs.model.items;

import net.gameslabs.model.IGatherable;
import net.gameslabs.model.Item;
import net.gameslabs.model.Skills;

// everything is static here, it should probably be singleton : private constructor with only public static methods (and possible private methods
// I'm really confused on these classes, I will leave it up to there. Either have it static or not. I don't understand why it's hybrid
// I don't have infinite time so I will leave it to there
public class CoalOre extends Item implements IGatherable {
    public static final CoalOre ITEM = new CoalOre();

    private static final String NAME = "Coal Ore";
    private static final String ID = "COAL_ORE";
    private static final Skills REQUIRED_SKILL = Skills.MINING;
    private static final int REQUIRED_LEVEL = 5;
    private static final int EXPERIENCE = 200;
    private static final int GATHER_AMOUNT = 6;

    private CoalOre()  {
        super(ID, NAME);
    }

    @Override
    public Skills getRequiredSkill() { return REQUIRED_SKILL; }

    @Override
    public int getRequiredLevel() { return REQUIRED_LEVEL; }

    @Override
    public int getExperience() {
        return EXPERIENCE;
    }

    @Override
    public int getGatherAmount() { return GATHER_AMOUNT; }
}
