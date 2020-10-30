package net.gameslabs.model;

import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.*;
import net.gameslabs.model.items.CoalOre;
import net.gameslabs.model.items.McChicken;
import net.gameslabs.model.items.RuniteOre;

import java.util.ArrayList;

public class Checker {
    private static ComponentRegistry REGISTRY;
    private static IPlayer MAIN_PLAYER;

    public static void checkAll(ComponentRegistry registry, IPlayer mainPlayer) {
        REGISTRY = registry;
        MAIN_PLAYER = mainPlayer;

        checkLevelsAndExpGain();
        checkPickingUpItems();
        checkDroppingItems();
        checkMining();
        checkDamage();
        checkEatingFood();
        checkDeath();
    }

    private static void checkPickingUpItems() {
        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 3) {
            throw new FailedCheck("Slot 1 should have 3 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }

        REGISTRY.sendEvent(new PickupItemEvent(new CoalOre(), 10));

        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 13) {
            throw new FailedCheck("Slot 1 should have 13 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }

        REGISTRY.sendEvent(new PickupItemEvent(new RuniteOre(), 100));

        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 13) {
            throw new FailedCheck("Slot 1 should have 13 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }
    }

    private static void checkDroppingItems() {
        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 13) {
            throw new FailedCheck("Slot 1 should have 13 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }

        REGISTRY.sendEvent(new DropItemEvent(new CoalOre(), 65));

        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 0) {
            throw new FailedCheck("Slot 0 should have 0 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 12) {
            throw new FailedCheck("Slot 1 should have 12 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }
    }

    private static void checkLevelsAndExpGain() {
        int expectedLevel = Stats.getLevelFromXp(10000 + MAIN_PLAYER.getStats().getXp(Skills.MINING));
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.MINING, 10000));
        int actualLevel = MAIN_PLAYER.getStats().getLevel(Skills.MINING);

        if (actualLevel != expectedLevel) throw new FailedCheck("ExpectedLevel: " + expectedLevel + "; ActualLevel: " + actualLevel);

        if (Helper.getAllSkills().size() != 3) {
            throw new FailedCheck("Expected to find 3 skills");
        }
    }

    private static void checkMining() {
        int mineAmount = 1000;

        int expectedLevel = Stats.getLevelFromXp((new RuniteOre()).getExperience() * mineAmount + MAIN_PLAYER.getStats().getXp(Skills.MINING));
        for (int i = 0; i < mineAmount; i++) {
            REGISTRY.sendEvent(new GatherEvent(new RuniteOre(), MAIN_PLAYER));
        }
        int actualLevel = MAIN_PLAYER.getStats().getLevel(Skills.MINING);
        if (actualLevel != expectedLevel) throw new FailedCheck("ExpectedLevel: " + expectedLevel + "; ActualLevel: " + actualLevel);

        if (!MAIN_PLAYER.getInventory().getSlot(20).getItem().equals(new RuniteOre()) || MAIN_PLAYER.getInventory().getSlot(20).getCount() != 64) {
            throw new FailedCheck("Slot 20 should have 64 RuniteOre");
        }
    }

    private static void checkDamage() {
        final int DAMAGE = MAIN_PLAYER.getHealth().getMaxHealth() - 1;
        int expectedHealth = MAIN_PLAYER.getHealth().getCurrentHealth() - DAMAGE;
        REGISTRY.sendEvent(new TakeDamageEvent(MAIN_PLAYER, DAMAGE));
        int actualHealth = MAIN_PLAYER.getHealth().getCurrentHealth();

        if (expectedHealth != actualHealth) {
            throw new FailedCheck("checkDamage failed. ExpectedHealth: " + expectedHealth + "; ActualHealth: " + actualHealth);
        }
    }

    private static void checkEatingFood() {
        McChicken mcChicken = new McChicken();
        int expectedHealth = MAIN_PLAYER.getHealth().getCurrentHealth() + mcChicken.getHealAmount();
        REGISTRY.sendEvent(new EatFoodEvent(MAIN_PLAYER, mcChicken));
        int actualHealth = MAIN_PLAYER.getHealth().getCurrentHealth();

        if (expectedHealth != actualHealth) {
            throw new FailedCheck("checkEatingFood failed. ExpectedHealth: " + expectedHealth + "; ActualHealth: " + actualHealth);
        }
    }

    private static void checkDeath() {
        REGISTRY.sendEvent(new DeathEvent(MAIN_PLAYER));
        ArrayList<Skills> skills = Helper.getAllSkills();
        for (int i = 0; i < skills.size(); i++) {
            if (MAIN_PLAYER.getStats().getLevel(skills.get(i)) != 1) {
                throw new FailedCheck("All skills should be 1 after death");
            }
        }

        ArrayList<InventorySlot> slots = MAIN_PLAYER.getInventory().getSlots();
        for (int i = 0; i < slots.size(); i++) {
            if (!slots.get(i).isEmpty()) {
                throw new FailedCheck("All inventory slots should be empty after death");
            }
        }

        if (MAIN_PLAYER.getHealth().getMissingHealth() > 0) {
            throw new FailedCheck("The player should not be missing any health immediately after dying");
        }
    }
}