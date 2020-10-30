package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.DeathEvent;
import net.gameslabs.events.EatFoodEvent;
import net.gameslabs.events.TakeDamageEvent;

public class HealthComponent extends Component {
    private int maxHealth;
    private int currentHealth;

    public HealthComponent(int maxHealth) {
        this.maxHealth = this.currentHealth = maxHealth;
    }

    @Override
    public void onLoad() {
        registerEvent(DeathEvent.class, this::onDeath);
        registerEvent(EatFoodEvent.class, this::onEatFood);
        registerEvent(TakeDamageEvent.class, this::onTakeDamage);
    }

    @Override
    public void onUnload() {
    }

    private void onDeath(DeathEvent event) {

    }

    private void onEatFood(EatFoodEvent event) {

    }

    private void onTakeDamage(TakeDamageEvent event) {

    }
}
