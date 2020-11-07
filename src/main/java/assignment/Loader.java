package assignment;

import assignment.components.*;
import net.gameslabs.api.Component;
import assignment.components.ExperienceComponent;
import net.gameslabs.model.Skills;

import java.util.ArrayList;

public class Loader {
    private static PlayerComponent MAIN_PLAYER;
    private static final ArrayList<Component> COMPONENTS = new ArrayList<>();

    public static void addExperienceComponent() {
        COMPONENTS.add(new ExperienceComponent());
    }

    public static void addPlayerComponents() {
        MAIN_PLAYER = addPlayer("nardnob");
        addPlayer("Mimiscout");
    }

    public static void addExpMultiplierComponent() {
        COMPONENTS.add(new ExpMultiplierComponent(2, Skills.CONSTRUCTION)); //ExpMultiplierComponent - double construction experience
    }

    public static void addMiningComponent() {
        COMPONENTS.add(new MiningComponent()); //MiningComponent - allows mining IGatherable items with the GatherEvent
    }

    public static PlayerComponent getMainPlayer() {
        return MAIN_PLAYER;
    }

    public static ArrayList<Component> getComponents() {
        return COMPONENTS;
    }

    private static PlayerComponent addPlayer(String name) {
        PlayerComponent player = PlayerComponent.createPlayer(name);

        COMPONENTS.add(player); //PlayerComponent - handles player functionality
        COMPONENTS.add((InventoryComponent)player.getInventory()); //InventoryComponent - gives the player an inventory
        COMPONENTS.add((HealthComponent)player.getHealth()); //HealthComponent - allows healing, taking damage, and even... death.

        return player;
    }
}
