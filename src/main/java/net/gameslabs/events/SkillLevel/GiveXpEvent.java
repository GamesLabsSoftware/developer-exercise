package net.gameslabs.events.SkillLevel;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.SkillLevel.Skill;

public class GiveXpEvent extends PlayerEvent {
    private final Skill skill;
    private int xp;

    public GiveXpEvent(Player player, Skill skill, int xp) {
        super(player);
        this.skill = skill;
        this.xp = xp;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
