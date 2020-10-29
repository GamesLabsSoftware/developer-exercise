package net.gameslabs.events;

import net.gameslabs.api.Event;

public class GetExpForLevelEvent extends Event {
    private final int level;
    private int exp;

    public GetExpForLevelEvent(int level) {
        this.level = level;
        this.exp = 0;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
