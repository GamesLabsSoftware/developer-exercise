package net.gameslabs.api;

import assignment.components.InventoryComponent;
import net.gameslabs.model.Stats;
import net.gameslabs.model.Skills;

public interface Player {
    String getId();
    String getName();
    Stats getStats();
    InventoryComponent getInventory();
    int getLevel(ComponentRegistry registry, Skills skill);
}
