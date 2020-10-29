package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Skill;

public class GiveXPToPlayerEvent extends PlayerEvent {
    private final Skill skill;
    private int xp;

    public GiveXPToPlayerEvent(Player player, Skill skill, int xp) {
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
