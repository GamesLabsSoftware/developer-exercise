package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.Mining.MiningEvent;
import net.gameslabs.events.SkillLevel.GetPlayerLevelEvent;
import net.gameslabs.events.SkillLevel.GetXPForLevelEvent;
import net.gameslabs.events.SkillLevel.GiveXpEvent;
import net.gameslabs.events.Items.GiveItemEvent;
import net.gameslabs.events.Items.HasItemEvent;
import net.gameslabs.events.Items.RemoveItemEvent;
import net.gameslabs.implem.PlayerImplem;
import net.gameslabs.model.SkillLevel.Skill;

import java.util.Arrays;

public class Assignment {

    protected final ComponentRegistry registry;
    private final Player mainPlayer;
    private final Player anotherPlayer;

    public Assignment(Component ... myComponentsToAdd) {
        registry = new ComponentRegistry();
        Arrays.asList(myComponentsToAdd).forEach(registry::registerComponent);
        registry.registerComponent(new ChartComponent());
        registry.load();
        mainPlayer = PlayerImplem.newPlayer("MyPlayer");
        anotherPlayer = PlayerImplem.newPlayer("SomeoneElse");
    }

    public final void run() {
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.CONSTRUCTION, 25));
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.EXPLORATION, 25));
        registry.sendEvent(new GiveItemEvent(mainPlayer, 1, 10));
        registry.sendEvent(new GiveItemEvent(mainPlayer, 1, -1));
        registry.sendEvent(new GiveItemEvent(mainPlayer, 2, 10));
        registry.sendEvent(new RemoveItemEvent(mainPlayer, 2, 5));

        registry.sendEvent(new MiningEvent(anotherPlayer, Rock.COAL));
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.MINING, 250));
        registry.sendEvent(new MiningEvent(mainPlayer, Rock.COAL));

        GetPlayerLevelEvent getPlayerLevel = new GetPlayerLevelEvent(mainPlayer, Skill.CONSTRUCTION);
        log("Player level", mainPlayer, getPlayerLevel.getLevel());
        runChecks();
        registry.unload();
    }

    private void runChecks() {
        if (getLevel(mainPlayer, Skill.EXPLORATION) != 1)
            throw new AssignmentFailed("Exploration XP should be set to level 1");

        if (getLevel(mainPlayer, Skill.CONSTRUCTION) != 2)
            throw new AssignmentFailed("Construction XP should be set to level 2");

        // doubles up to check that giveItem(1, 10) worked and also that
        // giveItem(1, -1) had no effect on inventory
        if (!hasItem(mainPlayer, 1, 10))
            throw new AssignmentFailed("Main player should have 10 items with id 1");

        if (!hasItem(mainPlayer, 2, 5))
            throw new AssignmentFailed("Main player should have exactly 5 items with id 2");

        if (hasItem(mainPlayer, 3, 1))
            throw new AssignmentFailed("Main player should have not have any items with id 2");

        if (hasItem(anotherPlayer, 1, 1))
            throw new AssignmentFailed("Other player should not have any items with id 1");

        if (hasItem(anotherPlayer, Rock.COAL.getOreItemId(), 1))
            throw new AssignmentFailed("Other player should not have successfully mined coal");

        if (!hasItem(mainPlayer, Rock.COAL.getOreItemId(), 1))
            throw new AssignmentFailed("Main player should have coal");

        if (getXp(mainPlayer, Skill.MINING) != 300)
            throw new AssignmentFailed("Main player should have had 300 xp");

        if (getLevel(mainPlayer, Skill.MINING) != 6)
            throw new AssignmentFailed("Main player should have level 6 mining");
    }

    private int getLevel(Player player, Skill skill) {
        GetPlayerLevelEvent getPlayerLevelEvent = new GetPlayerLevelEvent(player, skill);
        registry.sendEvent(getPlayerLevelEvent);
        return getPlayerLevelEvent.getLevel();
    }

    private boolean hasItem(Player player, int id, int amount) {
        HasItemEvent hasItemEvent = new HasItemEvent(player, id, amount);
        registry.sendEvent(hasItemEvent);
        return hasItemEvent.getResult();
    }

    private int getXp(Player player, Skill skill) {
        GetPlayerLevelEvent getPlayerLevelEvent = new GetPlayerLevelEvent(player, skill);
        registry.sendEvent(getPlayerLevelEvent);
        return getPlayerLevelEvent.getXp();
    }

    public void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }
}
