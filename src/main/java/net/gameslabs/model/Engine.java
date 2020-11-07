package net.gameslabs.model;

import assignment.components.PlayerComponent;
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
import java.util.HashMap;

public class Engine {
    protected final ComponentRegistry REGISTRY;
    private final HashMap<Players, IPlayer> PLAYERS;

    public Engine(HashMap<Players, IPlayer> players, ArrayList<Component> components) {
        PLAYERS = players;

        REGISTRY = new ComponentRegistry();
        components.forEach(REGISTRY::registerComponent);
        REGISTRY.load();
    }

    public final void run() {
        simulateGameRun(PLAYERS.get(Players.NARDNOB));
        simulateGameRun(PLAYERS.get(Players.MIMISCOUT));
        Checker.checkAll(REGISTRY, PLAYERS.get(Players.NARDNOB)); //test functionality
        Checker.checkAll(REGISTRY, PLAYERS.get(Players.MIMISCOUT)); //test functionality
        Helper.logPlayer(PLAYERS.get(Players.NARDNOB));
        Helper.logPlayer(PLAYERS.get(Players.MIMISCOUT));
        REGISTRY.unload();
    }

    private void simulateGameRun(IPlayer player) {
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(player, Skills.CONSTRUCTION, 25));
        REGISTRY.sendEvent(new GiveExpToPlayerEvent(player, Skills.EXPLORATION, 25));

        String inventory = player.getInventory().getId();
        REGISTRY.sendEvent(new PickupItemEvent(inventory, new CoalOre(), 67));
        REGISTRY.sendEvent(new PickupItemEvent(inventory, new TinOre(), 67));
        REGISTRY.sendEvent(new PickupItemEvent(inventory, new RuniteOre(), 2));
        REGISTRY.sendEvent(new PickupItemEvent(inventory, new Item(), 67));

        REGISTRY.sendEvent(new DropItemEvent(inventory, new TinOre(), 5));
        REGISTRY.sendEvent(new DropItemEvent(inventory, new RuniteOre(), 10));

        REGISTRY.sendEvent(new GatherEvent(player, new TinOre()));
        REGISTRY.sendEvent(new GatherEvent(player, new CoalOre()));
        REGISTRY.sendEvent(new GatherEvent(player, new CoalOre()));
    }
}
