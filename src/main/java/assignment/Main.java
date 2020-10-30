package assignment;

import assignment.components.ExpMultiplierComponent;
import assignment.components.PlayerComponent;
import net.gameslabs.api.Component;
import net.gameslabs.model.Assignment;
import net.gameslabs.model.Skills;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Component> components = new ArrayList<>();

        PlayerComponent mainPlayer = PlayerComponent.createPlayer("Player1");

        components.add(mainPlayer);
        components.add(mainPlayer.getInventory());
        components.add(new ExpMultiplierComponent(2, Skills.CONSTRUCTION)); //double construction exp

        new Assignment(mainPlayer, components).run();
    }
}
