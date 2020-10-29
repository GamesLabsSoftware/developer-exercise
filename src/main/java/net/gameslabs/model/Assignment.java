package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.GetPlayerLevelEvent;
import net.gameslabs.events.GiveXPToPlayerEvent;
import net.gameslabs.implem.PlayerImplem;

import java.util.Arrays;

public class Assignment {

    protected final ComponentRegistry REGISTRY;
    private final Player MAIN_PLAYER;

    public Assignment(Component ... myComponentsToAdd) {
        REGISTRY = new ComponentRegistry();
        Arrays.asList(myComponentsToAdd).forEach(REGISTRY::registerComponent);
        REGISTRY.registerComponent(new ChartComponent());
        REGISTRY.load();
        MAIN_PLAYER = PlayerImplem.newPlayer("MyPlayer");
    }

    public final void run() {
        REGISTRY.sendEvent(new GiveXPToPlayerEvent(MAIN_PLAYER, Skill.CONSTRUCTION, 25));
        REGISTRY.sendEvent(new GiveXPToPlayerEvent(MAIN_PLAYER, Skill.EXPLORATION, 25));

        log(MAIN_PLAYER);
        log("CONSTRUCTION: " + MAIN_PLAYER.getLevel(REGISTRY, Skill.CONSTRUCTION));
        log("EXPLORATION: " + MAIN_PLAYER.getLevel(REGISTRY, Skill.EXPLORATION));
        runChecks();
        REGISTRY.unload();
    }

    private void runChecks() {
        Checker.checkAll(REGISTRY, MAIN_PLAYER);
    }

    public void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }
}
