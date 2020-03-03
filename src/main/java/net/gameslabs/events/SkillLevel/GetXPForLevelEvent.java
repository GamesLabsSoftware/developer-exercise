package net.gameslabs.events.SkillLevel;

import net.gameslabs.api.Event;

public class GetXPForLevelEvent extends Event {
    private final int level;
    private int xp;

    public GetXPForLevelEvent(int level) {
        this.level = level;
        this.xp = 0;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}
