package net.gameslabs.api;

import net.gameslabs.model.Stats;
import net.gameslabs.model.Skills;

public interface Player {
    String getId();
    String getName();
    Stats getStats();
    int getLevel(ComponentRegistry registry, Skills skill);
}
