package net.gameslabs.api;

import net.gameslabs.model.Items.Inventory;

public interface Player {
    String getId();
    String getName();
    Inventory getInventory();
}
