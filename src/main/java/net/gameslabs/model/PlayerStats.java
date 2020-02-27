package net.gameslabs.model;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class PlayerStats {
    private Map<Skill, Integer> xpStats;

    public PlayerStats() {
        xpStats = new EnumMap<>(Skill.class);
    }

    public void setXp(Skill skill, int xp) {
        xpStats.put(skill, xp);
    }

    public int getXp(Skill skill) {
        return xpStats.getOrDefault(skill, 0);
    }

    public void addXp(Skill skill, int xp) {
        setXp(skill, getXp(skill) + xp);
    }
}
