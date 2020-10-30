package net.gameslabs.model;

public interface IGatherable {
    Skills getRequiredSkill();
    int getRequiredLevel();
    int getExperience();
    int getGatherAmount();
}