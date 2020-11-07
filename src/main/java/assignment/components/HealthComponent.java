package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IHealth;
import net.gameslabs.api.IPlayer;
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
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public int getMissingHealth() {
        return this.maxHealth - this.currentHealth;
    }

    private void onDeath(DeathEvent event) {
        this.currentHealth = this.maxHealth;
    }

    private void onEatFood(EatFoodEvent event) {
        this.currentHealth += event.getFood().getHealAmount();
        checkHealthBoundaries(event.getPlayer());
    }

    private void onTakeDamage(TakeDamageEvent event) {
        this.currentHealth -= event.getDamageAmount();
        checkHealthBoundaries(event.getPlayer());
    }

    private void checkHealthBoundaries(IPlayer player) {
        this.currentHealth = Math.min(this.currentHealth, this.maxHealth); //handle having too much health
        if (this.currentHealth <= 0) { //handle having too little health
            send(new DeathEvent(player));
        }
    }
}
