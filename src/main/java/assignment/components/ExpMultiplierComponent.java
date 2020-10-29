package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveExpToPlayerEvent;
import net.gameslabs.model.Skills;

public class ExpMultiplierComponent extends Component {
    private Skills skill;
    private double multiplier;

    public ExpMultiplierComponent(double multiplier) {
        this.skill = null; //ExpMultiplier will be applied to all skills
        this.multiplier = multiplier;
    }

    public ExpMultiplierComponent(double multiplier, Skills skill) {
        this.skill = skill;
        this.multiplier = multiplier;
    }

    @Override
    public void onLoad() {
        registerEvent(GiveExpToPlayerEvent.class, this::onGiveExp);
    }

    private void onGiveExp(GiveExpToPlayerEvent event) {
        double expMultiplier = 1.0;
        if(this.skill == null || event.getSkill() == this.skill) {
            expMultiplier = this.multiplier;
        }

        int exp = (int)Math.ceil(event.getExp() * expMultiplier);
        event.setExp(exp);
    }

    @Override
    public void onUnload() {
    }
}