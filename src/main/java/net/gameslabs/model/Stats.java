package net.gameslabs.model;

import java.util.EnumMap;
import java.util.Map;

public class Stats {
    private Map<Skills, Integer> xpStats;

    public Stats() {
        xpStats = new EnumMap<>(Skills.class);
    }

    public void setXp(Skills skill, int xp) {
        xpStats.put(skill, xp);
    }

    public int getXp(Skills skill) {
        return xpStats.getOrDefault(skill, 0);
    }

    public void addXp(Skills skill, int xp) {
        setXp(skill, getXp(skill) + xp);
    }
}