package net.gameslabs.model.items;

import net.gameslabs.model.IGatherable;
import net.gameslabs.model.Item;
import net.gameslabs.model.Skills;

public class CoalOre extends Item implements IGatherable {
    private static final String NAME = "Coal Ore";
    private static final String ID = "COAL_ORE";
    private static final Skills REQUIRED_SKILL = Skills.MINING;
    private static final int REQUIRED_LEVEL = 5;
    private static final int EXPERIENCE = 200;
    private static final int GATHER_AMOUNT = 6;

    @Override
    public String getId() { return this.ID; }

    @Override
    public String getName() { return this.NAME; }

    @Override
    public Skills getRequiredSkill() { return this.REQUIRED_SKILL; }

    @Override
    public int getRequiredLevel() { return this.REQUIRED_LEVEL; }

    @Override
    public int getExperience() {
        return this.EXPERIENCE;
    }

    @Override
    public int getGatherAmount() { return this.GATHER_AMOUNT; }
}
