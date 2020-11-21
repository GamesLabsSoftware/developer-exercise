package net.gameslabs.model;

import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.*;
import net.gameslabs.model.items.CoalOre;
import net.gameslabs.model.items.McChicken;
import net.gameslabs.model.items.RuniteOre;

import java.util.ArrayList;
import java.util.List;

public class Checker {
    private static ComponentRegistry REGISTRY;
    private static IPlayer PLAYER;

    public static void checkAll(ComponentRegistry registry, IPlayer player) {
        REGISTRY = registry;
        PLAYER = player;

        checkLevelsAndExpGain();
        checkPickingUpItems();
        checkDroppingItems();
        checkMining();
        checkDamage();
        checkEatingFood();
        checkDeath();

        Helper.log("Checks completed.");
    }

    private static void checkPickingUpItems() {
        if (PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (PLAYER.getInventory().getSlot(1).getCount() != 3) {
            throw new FailedCheck("Slot 1 should have 3 items");
        }
        if (PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }

        REGISTRY.sendEvent(new PickupItemEvent(PLAYER.getInventory().getId(), CoalOre.ITEM, 10));

        if (PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (PLAYER.getInventory().getSlot(1).getCount() != 13) {
            throw new FailedCheck("Slot 1 should have 13 items");
        }
        if (PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }

        REGISTRY.sendEvent(new PickupItemEvent(PLAYER.getInventory().getId(), new RuniteOre(), 100));

        if (PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (PLAYER.getInventory().getSlot(1).getCount() != 13) {
            throw new FailedCheck("Slot 1 should have 13 items");
        }
        if (PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }
    }

    private static void checkDroppingItems() {
        if (PLAYER.getInventory().getSlot(0).getCount() != 64) {
            throw new FailedCheck("Slot 0 should have 64 items");
        }
        if (PLAYER.getInventory().getSlot(1).getCount() != 13) {
            throw new FailedCheck("Slot 1 should have 13 items");
        }
        if (PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }

        REGISTRY.sendEvent(new DropItemEvent(PLAYER.getInventory().getId(), CoalOre.ITEM, 65));

        if (PLAYER.getInventory().getSlot(0).getCount() != 0) {
            throw new FailedCheck("Slot 0 should have 0 items");
        }
        if (PLAYER.getInventory().getSlot(1).getCount() != 12) {
            throw new FailedCheck("Slot 1 should have 12 items");
        }
        if (PLAYER.getInventory().getSlot(2).getCount() != 62) {
            throw new FailedCheck("Slot 2 should have 62 item");
        }
    }

    private static void checkLevelsAndExpGain() {
        int expectedLevel = Stats.getLevelFromXp(10000 + PLAYER.getStats().getXp(Skills.MINING));
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(PLAYER, Skills.MINING, 10000));
        int actualLevel = PLAYER.getStats().getLevel(Skills.MINING);

        if (actualLevel != expectedLevel) throw new FailedCheck("ExpectedLevel: " + expectedLevel + "; ActualLevel: " + actualLevel);

        if (Helper.getAllSkills().size() != 3) {
            throw new FailedCheck("Expected to find 3 skills");
        }
    }

    private static void checkMining() {
        int mineAmount = 1000;

        int expectedLevel = Stats.getLevelFromXp((new RuniteOre()).getExperience() * mineAmount + PLAYER.getStats().getXp(Skills.MINING));
        for (int i = 0; i < mineAmount; i++) {
            REGISTRY.sendEvent(new GatherEvent(PLAYER, new RuniteOre()));
        }
        int actualLevel = PLAYER.getStats().getLevel(Skills.MINING);
        if (actualLevel != expectedLevel) throw new FailedCheck("ExpectedLevel: " + expectedLevel + "; ActualLevel: " + actualLevel);

        if (!PLAYER.getInventory().getSlot(20).getItem().equals(new RuniteOre()) || PLAYER.getInventory().getSlot(20).getCount() != 64) {
            throw new FailedCheck("Slot 20 should have 64 RuniteOre");
        }
    }

    private static void checkDamage() {
        final int DAMAGE = PLAYER.getHealth().getMaxHealth() - 1;
        int expectedHealth = PLAYER.getHealth().getCurrentHealth() - DAMAGE;
        REGISTRY.sendEvent(new TakeDamageEvent(PLAYER, DAMAGE));
        int actualHealth = PLAYER.getHealth().getCurrentHealth();

        if (expectedHealth != actualHealth) {
            throw new FailedCheck("checkDamage failed. ExpectedHealth: " + expectedHealth + "; ActualHealth: " + actualHealth);
        }
    }

    private static void checkEatingFood() {
        McChicken mcChicken = new McChicken();
        int expectedHealth = PLAYER.getHealth().getCurrentHealth() + mcChicken.getHealAmount();
        REGISTRY.sendEvent(new EatFoodEvent(PLAYER, mcChicken));
        int actualHealth = PLAYER.getHealth().getCurrentHealth();

        if (expectedHealth != actualHealth) {
            throw new FailedCheck("checkEatingFood failed. ExpectedHealth: " + expectedHealth + "; ActualHealth: " + actualHealth);
        }
    }

    private static void checkDeath() {
        REGISTRY.sendEvent(new DeathEvent(PLAYER));
        // Interface..
        List<Skills> skills = Helper.getAllSkills();
        // foreach
        for (Skills skill : skills) {
            if (PLAYER.getStats().getLevel(skill) != 1) {
                throw new FailedCheck("All skills should be 1 after death");
            }
        }

        // same here
        List<InventorySlot> slots = PLAYER.getInventory().getSlots();
        for (InventorySlot slot : slots) {
            if (!slot.isEmpty()) {
                throw new FailedCheck("All inventory slots should be empty after death");
            }
        }

        if (PLAYER.getHealth().getMissingHealth() > 0) {
            throw new FailedCheck("The player should not be missing any health immediately after dying");
        }
    }
}