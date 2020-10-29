package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.GiveExpToPlayerEvent;
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
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.CONSTRUCTION, 25));
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.EXPLORATION, 25));

        Helper.log(MAIN_PLAYER);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "CONSTRUCTION", Skills.CONSTRUCTION);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "EXPLORATION", Skills.EXPLORATION);
        Checker.checkAll(REGISTRY, MAIN_PLAYER);
        REGISTRY.unload();
    }
}
