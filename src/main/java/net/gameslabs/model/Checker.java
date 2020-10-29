package net.gameslabs.model;

import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevelEvent;

public class Checker {
    private static ComponentRegistry REGISTRY;
    private static Player MAIN_PLAYER;

    public static void checkAll(ComponentRegistry registry, Player mainPlayer) {
        REGISTRY = registry;
        MAIN_PLAYER = mainPlayer;

        checkLevels();
    }

    private static void checkLevels() {
        if (getLevel(Skill.EXPLORATION) != 1) throw new AssignmentFailed("Exploration should be set to level 1");
        if (getLevel(Skill.CONSTRUCTION) != 2) throw new AssignmentFailed("Construction should be set to level 2");
    }

    private static int getLevel(Skill skill) {
        GetPlayerLevelEvent getPlayerLevel = new GetPlayerLevelEvent(MAIN_PLAYER, skill);
        REGISTRY.sendEvent(getPlayerLevel);
        return getPlayerLevel.getLevel();
    }
}
