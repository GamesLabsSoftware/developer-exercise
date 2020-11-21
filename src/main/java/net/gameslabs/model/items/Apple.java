package net.gameslabs.model.items;

import net.gameslabs.model.IFood;

public class Apple implements IFood {
    private static final int HEAL_AMOUNT = 15;

    @Override
    public int getHealAmount() {
        return HEAL_AMOUNT;
    }
}
