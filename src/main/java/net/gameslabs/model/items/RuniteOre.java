package net.gameslabs.model.items;

import net.gameslabs.model.IGatherable;
import net.gameslabs.model.Item;
import net.gameslabs.model.Skills;

public class RuniteOre extends Item implements IGatherable {
    private static final String NAME = "Runite Ore";
    private static final String ID = "RUNITE_ORE";
    private static final Skills REQUIRED_SKILL = Skills.MINING;
    private static final int REQUIRED_LEVEL = 99;
    private static final int EXPERIENCE = 600;
    private static final int GATHER_AMOUNT = 1;

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
