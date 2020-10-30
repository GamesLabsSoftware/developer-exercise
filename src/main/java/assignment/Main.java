package assignment;

import assignment.components.*;
import net.gameslabs.api.Component;
import net.gameslabs.model.Engine;
import net.gameslabs.model.Skills;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Component> components = new ArrayList<>();

        PlayerComponent mainPlayer = PlayerComponent.createPlayer("nardnob");

        components.add(mainPlayer); //PlayerComponent - handles player functionality
        components.add((InventoryComponent)mainPlayer.getInventory()); //InventoryComponent - gives the player an inventory
        components.add((HealthComponent)mainPlayer.getHealth()); //HealthComponent - allows healing, taking damage, and even... death.
        components.add(new ExpMultiplierComponent(2, Skills.CONSTRUCTION)); //ExpMultiplierComponent - double construction experience
        components.add(new MiningComponent()); //MiningComponent - allows mining IGatherable items with the GatherEvent

        new Engine(mainPlayer, components).run();
    }
}
