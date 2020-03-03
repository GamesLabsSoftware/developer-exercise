package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.GetPlayerLevelEvent;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.events.Items.GiveItemEvent;
import net.gameslabs.events.Items.HasItemEvent;
import net.gameslabs.implem.PlayerImplem;

import java.util.Arrays;

public class Assignment {

    protected final ComponentRegistry registry;
    private final Player mainPlayer;

    public Assignment(Component ... myComponentsToAdd) {
        registry = new ComponentRegistry();
        Arrays.asList(myComponentsToAdd).forEach(registry::registerComponent);
        registry.registerComponent(new ChartComponent());
        registry.load();
        mainPlayer = PlayerImplem.newPlayer("MyPlayer");
    }

    public final void run() {
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.CONSTRUCTION, 25));
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.EXPLORATION, 25));
        registry.sendEvent(new GiveItemEvent(mainPlayer, 1, 10));
        GetPlayerLevelEvent getPlayerLevel = new GetPlayerLevelEvent(mainPlayer, Skill.CONSTRUCTION);
        log("Player level", mainPlayer, getPlayerLevel.getLevel());
        runChecks();
        registry.unload();
    }

    private void runChecks() {
        if (getLevel(Skill.EXPLORATION) != 1)
            throw new AssignmentFailed("Exploration XP should be set to level 1");

        if (getLevel(Skill.CONSTRUCTION) != 2)
            throw new AssignmentFailed("Construction XP should be set to level 2");

        if (!hasItem(1, 10))
            throw new AssignmentFailed("Player should have 10 items with id 1");

        if (hasItem(2, 1))
            throw new AssignmentFailed("Player should have not have any items with id 2");
    }

    private int getLevel(Skill skill) {
        GetPlayerLevelEvent getPlayerLevel = new GetPlayerLevelEvent(mainPlayer, skill);
        registry.sendEvent(getPlayerLevel);
        return getPlayerLevel.getLevel();
    }

    private boolean hasItem(int id, int amount) {
        HasItemEvent hasItemEvent = new HasItemEvent(mainPlayer, id, amount);
        registry.sendEvent(hasItemEvent);
        return hasItemEvent.getResult();
    }

    public void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }
}
