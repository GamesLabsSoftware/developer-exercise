package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IHealth;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.*;

public class HealthComponent extends Component implements IHealth {
    private String id;
    private int maxHealth;
    private int currentHealth;

    public HealthComponent(String id, int maxHealth) {
        this.id = id;
        this.maxHealth = this.currentHealth = maxHealth;
    }

    @Override
    public void onLoad() {
        registerEvent(DeathEvent.class, this::onDeath);
        registerEvent(EatFoodEvent.class, this::onEatFood);
        registerEvent(TakeDamageEvent.class, this::onTakeDamage);
    }

    @Override
    public void onUnload() {}

    @Override
    public int getCurrentHealth() { return this.currentHealth; }

    @Override
    public int getMaxHealth() { return this.maxHealth; }

    @Override
    public int getMissingHealth() {
        return this.maxHealth - this.currentHealth;
    }

    public String getId() { return this.id; }

    private void onDeath(DeathEvent event) {
        if (event.getPlayer().getHealth().getId() == this.id) {
            this.currentHealth = this.maxHealth;
        }
    }

    private void onEatFood(EatFoodEvent event) {
        if (event.getPlayer().getHealth().getId() == this.id) {
            this.currentHealth += event.getFood().getHealAmount();
            checkHealthBoundaries(event.getPlayer());
        }
    }

    private void onTakeDamage(TakeDamageEvent event) {
        if (event.getPlayer().getHealth().getId() == this.id) {
            this.currentHealth -= event.getDamageAmount();
            checkHealthBoundaries(event.getPlayer());
        }
    }

    private void checkHealthBoundaries(IPlayer player) {
        if (player.getHealth().getId() == this.id) {
            this.currentHealth = Math.min(this.currentHealth, this.maxHealth); //handle having too much health
            if (this.currentHealth <= 0) { //handle having too little health
                send(new DeathEvent(player));
            }
        }
    }
}
