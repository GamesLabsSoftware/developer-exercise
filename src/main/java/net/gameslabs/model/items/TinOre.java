package net.gameslabs.model.items;

import net.gameslabs.model.IGatherable;
import net.gameslabs.model.Item;
import net.gameslabs.model.Skills;

public class TinOre extends Item implements IGatherable {
    private static final String NAME = "Tin Ore";
    private static final String ID = "TIN_ORE";
    private static final Skills REQUIRED_SKILL = Skills.MINING;
    private static final int REQUIRED_LEVEL = 1;
    private static final int EXPERIENCE = 75;
    private static final int GATHER_AMOUNT = 3;

    @Override
    public String getId() { return this.ID; }

    @Override
    public String getName() { return this.NAME; }

    @Override
    public Skills getRequiredSkill() { return this.REQUIRED_SKILL; }

    @Override
    public int getRequiredLevel() { return this.REQUIRED_LEVEL; }

    @Override
    public int getExperience() { return this.EXPERIENCE; }

    @Override
    public int getGatherAmount() { return this.GATHER_AMOUNT; }
}
