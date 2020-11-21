package assignment;

import assignment.components.*;
import net.gameslabs.api.Component;
import assignment.components.ExperienceComponent;
import net.gameslabs.api.IPlayer;
import net.gameslabs.model.Players;
import net.gameslabs.model.Skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Loader {
    // Same here, use the interfaces
    private static Map<Players, IPlayer> PLAYERS = new HashMap<>();
    private static final List<Component> COMPONENTS = new ArrayList<>();

    public static void addExperienceComponent() {
        COMPONENTS.add(new ExperienceComponent());
    }

    public static void addPlayerComponents() {
        PLAYERS.put(Players.NARDNOB, addPlayer("nardnob"));
        PLAYERS.put(Players.MIMISCOUT, addPlayer("Mimiscout"));
    }

    public static void addExpMultiplierComponent() {
        COMPONENTS.add(new ExpMultiplierComponent(2, Skills.CONSTRUCTION)); //ExpMultiplierComponent - double construction experience
    }

    public static void addMiningComponent() {
        COMPONENTS.add(new MiningComponent()); //MiningComponent - allows mining IGatherable items with the GatherEvent
    }

    public static Map<Players, IPlayer> getPlayers() {
        return PLAYERS;
    }

    public static List<Component> getComponents() {
        return COMPONENTS;
    }

    private static PlayerComponent addPlayer(String name) {
        PlayerComponent player = PlayerComponent.createPlayer(name);

        COMPONENTS.add(player); //PlayerComponent - handles player functionality
        COMPONENTS.add((InventoryComponent)player.getInventory()); //InventoryComponent - gives the player an inventory
        COMPONENTS.add((HealthComponent)player.getHealth()); //HealthComponent - allows healing, taking damage, and even... death.

        return player;
    }

    // How do you remove a player?
}
