package assignment;

import assignment.components.FiremakingComponent;
import assignment.components.InventoryComponent;
import assignment.components.MiningComponent;
import assignment.components.XPBoosterComponent;
import net.gameslabs.model.Assignment;

public class Main {

    public static void main(String[] args) {
        new Assignment(
                new FiremakingComponent(),
                new InventoryComponent(),
                new MiningComponent(),
                new XPBoosterComponent()
        ).run();
    }
}
