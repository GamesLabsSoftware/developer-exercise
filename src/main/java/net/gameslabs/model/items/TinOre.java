package net.gameslabs.model.items;

import net.gameslabs.model.IGatherable;
import net.gameslabs.model.Item;
import net.gameslabs.model.Skills;

public class TinOre extends Item implements IGatherable {
    private static final String NAME = "Tin Ore";
    private static final String ID = "TIN_ORE";
    private Skills REQUIRED_SKILL = Skills.MINING;
    private int REQUIRED_LEVEL = 1;
    private int EXPERIENCE = 75;
    private int GATHER_AMOUNT = 3;

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
