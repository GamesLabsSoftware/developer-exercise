package net.gameslabs.model;

import assignment.components.ExperienceComponent;

import java.util.EnumMap;
import java.util.Map;

public class Stats {
    private final String id;
    private final Map<Skills, Integer> experience;

    public Stats(String id) {
        this.id = id;
        experience = new EnumMap<>(Skills.class);
    }

    public void setXp(Skills skill, int xp) {
        experience.put(skill, xp);
    }

    public int getLevel(Skills skill) {
        int skillXp = getXp(skill);
        int level = getLevelFromXp(skillXp);
        return level;
    }

    public static int getLevelFromXp(int xp) {
        return 1 + Math.floorDiv(xp, ExperienceComponent.XP_STEP);
    }

    public int getXp(Skills skill) {
        return experience.getOrDefault(skill, 0);
    }

    public void addXp(Skills skill, int xp) {
        setXp(skill, getXp(skill) + xp);
    }
}
