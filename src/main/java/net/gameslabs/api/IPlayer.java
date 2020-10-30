package net.gameslabs.api;

import net.gameslabs.model.Stats;

public interface IPlayer {
    String getId();
    String getName();
    Stats getStats();
    IInventory getInventory();
    IHealth getHealth();
}
