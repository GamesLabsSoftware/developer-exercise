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

import java.util.List;
import java.util.Map;

public class Engine {
    // Not sure why this is protected
    // non static fields shouldn't be in capital letters
    protected final ComponentRegistry registry;

    // Use the interfaces..
    private final Map<Players, IPlayer> players;

    // Use the interfaces..
    public Engine(Map<Players, IPlayer> players, List<Component> components) {
        this.players = players;

        registry = new ComponentRegistry();
        components.forEach(registry::registerComponent);
        registry.load();
    }

    public final void run() {
        simulateGameRun(players.get(Players.NARDNOB));
        simulateGameRun(players.get(Players.MIMISCOUT));
        Checker.checkAll(registry, players.get(Players.NARDNOB)); //test functionality
        Checker.checkAll(registry, players.get(Players.MIMISCOUT)); //test functionality
        Helper.logPlayer(players.get(Players.NARDNOB));
        Helper.logPlayer(players.get(Players.MIMISCOUT));
        registry.unload();
    }

    private void simulateGameRun(IPlayer player) {
        registry.sendEvent(new GiveExpToPlayerEvent(player, Skills.CONSTRUCTION, 25));
        registry.sendEvent(new GiveExpToPlayerEvent(player, Skills.EXPLORATION, 25));

        String inventory = player.getInventory().getId();
        registry.sendEvent(new PickupItemEvent(inventory, CoalOre.ITEM, 67));
        registry.sendEvent(new PickupItemEvent(inventory, new TinOre(), 67));
        registry.sendEvent(new PickupItemEvent(inventory, new RuniteOre(), 2));
        registry.sendEvent(new PickupItemEvent(inventory, new Item(), 67));

        registry.sendEvent(new DropItemEvent(inventory, new TinOre(), 5));
        registry.sendEvent(new DropItemEvent(inventory, new RuniteOre(), 10));

        registry.sendEvent(new GatherEvent(player, new TinOre()));
        registry.sendEvent(new GatherEvent(player, CoalOre.ITEM));
        registry.sendEvent(new GatherEvent(player, CoalOre.ITEM));
    }
}
