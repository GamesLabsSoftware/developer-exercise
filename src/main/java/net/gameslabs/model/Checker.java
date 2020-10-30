package net.gameslabs.model;

import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.events.DropItemEvent;
import net.gameslabs.events.PickupItemEvent;

public class Checker {
    private static ComponentRegistry REGISTRY;
    private static Player MAIN_PLAYER;

    public static void checkAll(ComponentRegistry registry, Player mainPlayer) {
        REGISTRY = registry;
        MAIN_PLAYER = mainPlayer;

        //checkLevels();
        //checkPickingUpItems();
        //checkDroppingItems();
    }

    private static void checkPickingUpItems() {
        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 0) {
            throw new AssignmentFailed("Slot 0 should be empty");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 5) {
            throw new AssignmentFailed("Slot 1 should have 5 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 0) {
            throw new AssignmentFailed("Slot 2 should be empty");
        }

        REGISTRY.sendEvent(new PickupItemEvent(new Item(), 10));

        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 0) {
            throw new AssignmentFailed("Slot 1 should have 0 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 15) {
            throw new AssignmentFailed("Slot 1 should have 15 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 0) {
            throw new AssignmentFailed("Slot 2 should be empty");
        }

        REGISTRY.sendEvent(new PickupItemEvent(new Item(), 100));

        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 51) {
            throw new AssignmentFailed("Slot 1 should have 51 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 64) {
            throw new AssignmentFailed("Slot 1 should have 64 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 0) {
            throw new AssignmentFailed("Slot 2 should be empty");
        }
    }

    private static void checkDroppingItems() {
        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 51) {
            throw new AssignmentFailed("Slot 1 should have 51 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 64) {
            throw new AssignmentFailed("Slot 1 should have 64 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 0) {
            throw new AssignmentFailed("Slot 2 should be empty");
        }

        REGISTRY.sendEvent(new DropItemEvent(new Item(), 52));

        if (MAIN_PLAYER.getInventory().getSlot(0).getCount() != 0) {
            throw new AssignmentFailed("Slot 1 should have 0 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(1).getCount() != 63) {
            throw new AssignmentFailed("Slot 1 should have 63 items");
        }
        if (MAIN_PLAYER.getInventory().getSlot(2).getCount() != 0) {
            throw new AssignmentFailed("Slot 2 should be empty");
        }
    }

    private static void checkLevels() {
        if (MAIN_PLAYER.getStats().getLevel(Skills.EXPLORATION) != 1) throw new AssignmentFailed("Exploration should be set to level 1");
        if (MAIN_PLAYER.getStats().getLevel(Skills.CONSTRUCTION) != 2) throw new AssignmentFailed("Construction should be set to level 2");
    }
}
