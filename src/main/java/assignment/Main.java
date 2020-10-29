package assignment;

import assignment.components.InventoryComponent;
import assignment.components.ExpMultiplierComponent;
import net.gameslabs.model.Assignment;
import net.gameslabs.model.Skills;

public class Main {

    public static void main(String[] args) {
        new Assignment(
            new ExpMultiplierComponent(2, Skills.CONSTRUCTION),
            new InventoryComponent(37)
        ).run();
    }
}
