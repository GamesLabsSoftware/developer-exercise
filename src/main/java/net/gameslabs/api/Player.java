package net.gameslabs.api;

import net.gameslabs.model.Skill;

public interface Player {
    String getId();
    String getName();
    int getLevel(ComponentRegistry registry, Skill skill);
}
