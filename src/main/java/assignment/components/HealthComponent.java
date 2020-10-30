package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IHealth;
import net.gameslabs.events.*;

public class HealthComponent extends Component implements IHealth {
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

    @Override
    public int getCurrentHealth() {
        return this.currentHealth;
    }

    @Override
    public int getMissingHealth() {
        return this.maxHealth - this.currentHealth;
    }

    private void onDeath(DeathEvent event) {
        send(new DropAllItemsEvent(event.getPlayer()));
        send(new ResetExpEvent(event.getPlayer()));
        this.currentHealth = this.maxHealth;
    }

    private void onEatFood(EatFoodEvent event) {

    }

    private void onTakeDamage(TakeDamageEvent event) {

    }
}
