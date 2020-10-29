package net.gameslabs.api;

import net.gameslabs.model.PlayerStats;
import net.gameslabs.model.Skills;

public interface Player {
    String getId();
    String getName();
    PlayerStats getStats();
    int getLevel(ComponentRegistry registry, Skills skill);
}
