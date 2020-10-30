package net.gameslabs.model.items;

import net.gameslabs.model.IFood;

public class McChicken implements IFood {
    private static final int HEAL_AMOUNT = 55;

    @Override
    public int getHealAmount() {
        return HEAL_AMOUNT;
    }
}
