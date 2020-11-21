package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveExpToPlayerEvent;
import net.gameslabs.model.Skills;

public class ExpMultiplierComponent extends Component {
    private final Skills skill;
    private final double multiplier;

    public ExpMultiplierComponent(double multiplier) {
        // Would rather use one constructor with default parameters
        this(multiplier, null);
    }

    /**
     *
     * @param multiplier
     * @param skill if null, the multiplier will be applied to all skills
     */
    public ExpMultiplierComponent(double multiplier, Skills skill) {
        this.skill = skill;
        this.multiplier = multiplier;
    }

    @Override
    public void onLoad() {
        registerEvent(GiveExpToPlayerEvent.class, this::onGiveExp);
    }

    @Override
    public void onUnload() {
    }

    private void onGiveExp(GiveExpToPlayerEvent event) {
        boolean applyToAllSkills = this.skill == null;
        // this one is arguable, but it's usually better to use .equals
        boolean isRelevantEvent = event.getSkill().equals(this.skill);

        if (applyToAllSkills || isRelevantEvent) {
            int exp = (int) Math.ceil(event.getExp() * this.multiplier);
            event.setExp(exp);
        }
    }
}