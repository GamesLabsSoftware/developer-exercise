package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Skills;

public class GetPlayerLevelEvent extends PlayerEvent {
    private final Skills skill;
    private int level;

    public GetPlayerLevelEvent(Player player, Skills skill) {
        super(player);
        this.skill = skill;
    }

    public Skills getSkill() {
        return this.skill;
    }
    public int getLevel() { return this.level; }
    public void setLevel(int level) {
        this.level = level;
    }
}
