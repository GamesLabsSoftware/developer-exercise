package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Skill;

public class MyXPBoosterComponent extends Component {
    private static final double XP_MULTIPLIER_BASE = 1.0;
    private static double XP_MULTIPLIER_CONSTRUCTION = 2.0;
    private static double XP_MULTIPLIER_EXPLORATION = 1.0;

    @Override
    public void onLoad() {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    private void onGiveXP(GiveXpEvent event) {
        double xpMultiplier = 1.0;
        switch(event.getSkill()) {
            case CONSTRUCTION: xpMultiplier = this.XP_MULTIPLIER_CONSTRUCTION; break;
            case EXPLORATION: xpMultiplier = this.XP_MULTIPLIER_EXPLORATION; break;
        }

        int finalXp = (int)Math.ceil(event.getXp() * this.XP_MULTIPLIER_BASE * xpMultiplier);
        event.setXp(finalXp);
    }

    @Override
    public void onUnload() {
        // Do nothing
    }

    public double getXpMultiplier(Skill skill) {
        switch(skill) {
            case CONSTRUCTION: return this.XP_MULTIPLIER_CONSTRUCTION;
            case EXPLORATION: return this.XP_MULTIPLIER_EXPLORATION;
            default: return 0;
        }
    }

    public void setXpMultiplier(Skill skill, double multiplier) {
        switch(skill) {
            case CONSTRUCTION: this.XP_MULTIPLIER_CONSTRUCTION = multiplier;
            case EXPLORATION: this.XP_MULTIPLIER_EXPLORATION = multiplier;
        }
    }
}