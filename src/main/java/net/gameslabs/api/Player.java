package net.gameslabs.api;

import assignment.components.InventoryComponent;
import net.gameslabs.model.Stats;

public interface Player {
    String getId();
    String getName();
    Stats getStats();
    InventoryComponent getInventory();
}
