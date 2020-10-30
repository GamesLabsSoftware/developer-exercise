package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.GiveExpToPlayerEvent;
import assignment.components.PlayerComponent;
import net.gameslabs.events.PickupItemEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Assignment {
    protected final ComponentRegistry REGISTRY;
    private final Player MAIN_PLAYER;

    public Assignment(Player mainPlayer, ArrayList<Component> components) {
        REGISTRY = new ComponentRegistry();
        MAIN_PLAYER = mainPlayer;

        components.forEach(REGISTRY::registerComponent);
        REGISTRY.registerComponent(new ChartComponent());
        REGISTRY.load();
    }

    public final void run() {
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.CONSTRUCTION, 25));
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.EXPLORATION, 25));
        REGISTRY.sendEvent(new PickupItemEvent(new Item(), 200));

        Helper.log(MAIN_PLAYER);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "CONSTRUCTION", Skills.CONSTRUCTION);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "EXPLORATION", Skills.EXPLORATION);
        Helper.log(MAIN_PLAYER.getInventory());
        Checker.checkAll(REGISTRY, MAIN_PLAYER);
        REGISTRY.unload();
    }
}
