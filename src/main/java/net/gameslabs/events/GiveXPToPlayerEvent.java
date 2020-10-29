package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Skills;

public class GiveXPToPlayerEvent extends PlayerEvent {
    private final Skills skill;
    private int xp;

    public GiveXPToPlayerEvent(Player player, Skills skill, int xp) {
        super(player);
        this.skill = skill;
        this.xp = xp;
    }

    public Skills getSkill() {
        return skill;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
