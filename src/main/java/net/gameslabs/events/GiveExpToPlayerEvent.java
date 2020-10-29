package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Skills;

public class GiveExpToPlayerEvent extends PlayerEvent {
    private final Skills skill;
    private int exp;

    public GiveExpToPlayerEvent(Player player, Skills skill, int exp) {
        super(player);
        this.skill = skill;
        this.exp = exp;
    }

    public Skills getSkill() {
        return skill;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
