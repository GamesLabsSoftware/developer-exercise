package net.gameslabs.model;

import net.gameslabs.components.ChartComponent;

import java.util.EnumMap;
import java.util.Map;

public class Stats {
    private Map<Skills, Integer> experience;

    public Stats() {
        experience = new EnumMap<>(Skills.class);
    }

    public void setXp(Skills skill, int xp) {
        experience.put(skill, xp);
    }

    public int getLevel(Skills skill) {
        int skillXp = getXp(skill);
        return getLevelFromXp(skillXp);
    }

    private int getLevelFromXp(int xp) {
        return 1 + Math.floorDiv(xp, ChartComponent.XP_STEP);
    }

    public int getXp(Skills skill) {
        return experience.getOrDefault(skill, 0);
    }

    public void addXp(Skills skill, int xp) {
        setXp(skill, getXp(skill) + xp);
    }
}
