package net.gameslabs.api;

import net.gameslabs.model.Stats;

public interface IPlayer {
    String getId();
    String getName(); // Never used, but that's fine for the purpose of the assignment
    Stats getStats();
    IInventory getInventory();
    IHealth getHealth();
}
