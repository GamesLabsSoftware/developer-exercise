package assignment;

import assignment.components.InventoryComponent;
import assignment.components.MiningComponent;
import assignment.components.MyXPBoosterComponent;
import assignment.components.SpellCasterComponent;
import net.gameslabs.model.Assignment;

public class Main {

    public static void main(String[] args) {
        new Assignment(
            new MyXPBoosterComponent(),
            new InventoryComponent(),
            new MiningComponent(),
            new SpellCasterComponent()
        ).run();
    }
}
