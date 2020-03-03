package net.gameslabs.events.SkillLevel;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.SkillLevel.Skill;

public class GetPlayerLevelEvent extends PlayerEvent {
    private final Skill skill;
    private int level;
    private int xp;

    public GetPlayerLevelEvent(Player player, Skill skill) {
        super(player);
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
