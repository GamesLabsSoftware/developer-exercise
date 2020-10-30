package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.DropItemEvent;
import net.gameslabs.events.GatherEvent;
import net.gameslabs.events.GiveExpToPlayerEvent;
import assignment.components.PlayerComponent;
import net.gameslabs.events.PickupItemEvent;
import net.gameslabs.model.items.CoalOre;
import net.gameslabs.model.items.RuniteOre;
import net.gameslabs.model.items.TinOre;

import java.util.ArrayList;
import java.util.Arrays;

public class Engine {
    protected final ComponentRegistry REGISTRY;
    private final Player MAIN_PLAYER;

    public Engine(Player mainPlayer, ArrayList<Component> components) {
        REGISTRY = new ComponentRegistry();
        MAIN_PLAYER = mainPlayer;

        components.forEach(REGISTRY::registerComponent);
        REGISTRY.registerComponent(new ChartComponent());
        REGISTRY.load();
    }

    public final void run() {
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.CONSTRUCTION, 25));
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.EXPLORATION, 25));

        REGISTRY.sendEvent(new PickupItemEvent(new CoalOre(), 67));
        REGISTRY.sendEvent(new PickupItemEvent(new TinOre(), 67));
        REGISTRY.sendEvent(new PickupItemEvent(new RuniteOre(), 2));
        REGISTRY.sendEvent(new PickupItemEvent(new Item(), 67));

        REGISTRY.sendEvent(new DropItemEvent(new TinOre(), 5));
        REGISTRY.sendEvent(new DropItemEvent(new RuniteOre(), 10));

        REGISTRY.sendEvent(new GatherEvent(new TinOre(), MAIN_PLAYER));
        REGISTRY.sendEvent(new GatherEvent(new CoalOre(), MAIN_PLAYER));
        REGISTRY.sendEvent(new GatherEvent(new CoalOre(), MAIN_PLAYER));

        Checker.checkAll(REGISTRY, MAIN_PLAYER);

        Helper.log(MAIN_PLAYER);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "CONSTRUCTION", Skills.CONSTRUCTION);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "EXPLORATION", Skills.EXPLORATION);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "MINING", Skills.MINING);
        Helper.log(MAIN_PLAYER.getInventory());

        REGISTRY.unload();
    }
}
