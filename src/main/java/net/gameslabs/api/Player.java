package net.gameslabs.api;

import net.gameslabs.model.Inventory;

public interface Player {
    String getId();
    String getName();
    Inventory getInventory();
}
