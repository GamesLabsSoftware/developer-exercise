package net.gameslabs.model;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.DropItemEvent;
import net.gameslabs.events.GatherEvent;
import net.gameslabs.events.GiveExpToPlayerEvent;
import net.gameslabs.events.PickupItemEvent;
import net.gameslabs.model.items.CoalOre;
import net.gameslabs.model.items.RuniteOre;
import net.gameslabs.model.items.TinOre;

import java.util.ArrayList;

public class Engine {
    protected final ComponentRegistry REGISTRY;
    private final IPlayer MAIN_PLAYER;

    public Engine(IPlayer mainPlayer, ArrayList<Component> components) {
        REGISTRY = new ComponentRegistry();
        MAIN_PLAYER = mainPlayer;

        components.forEach(REGISTRY::registerComponent);
        REGISTRY.load();
    }

    public final void run() {
        simulateEvents();
        Checker.checkAll(REGISTRY, MAIN_PLAYER); //test functionality
        writeOutput();
        REGISTRY.unload();
    }

    private void simulateEvents() {
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.CONSTRUCTION, 25));
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(MAIN_PLAYER, Skills.EXPLORATION, 25));

        String mainPlayerInv = MAIN_PLAYER.getInventory().getId();
        REGISTRY.sendEvent(new PickupItemEvent(mainPlayerInv, new CoalOre(), 67));
        REGISTRY.sendEvent(new PickupItemEvent(mainPlayerInv, new TinOre(), 67));
        REGISTRY.sendEvent(new PickupItemEvent(mainPlayerInv, new RuniteOre(), 2));
        REGISTRY.sendEvent(new PickupItemEvent(mainPlayerInv, new Item(), 67));

        REGISTRY.sendEvent(new DropItemEvent(mainPlayerInv, new TinOre(), 5));
        REGISTRY.sendEvent(new DropItemEvent(mainPlayerInv, new RuniteOre(), 10));

        REGISTRY.sendEvent(new GatherEvent(MAIN_PLAYER, new TinOre()));
        REGISTRY.sendEvent(new GatherEvent(MAIN_PLAYER, new CoalOre()));
        REGISTRY.sendEvent(new GatherEvent(MAIN_PLAYER, new CoalOre()));
    }

    private void writeOutput() {
        Helper.log(MAIN_PLAYER);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "CONSTRUCTION", Skills.CONSTRUCTION);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "EXPLORATION", Skills.EXPLORATION);
        Helper.logSkill(REGISTRY, MAIN_PLAYER, "MINING", Skills.MINING);
        Helper.log(MAIN_PLAYER.getInventory());
    }
}
