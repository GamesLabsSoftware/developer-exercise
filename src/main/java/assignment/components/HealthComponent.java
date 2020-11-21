package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IHealth;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.*;

/**
 * A bit mis-understanding with the components is the way a component system interacts with other components
 * A component shouldn't really be stored in a model (e.g. player.getHealth() : the health here shouldn't be a component.
 * It breaks the re-usability of components quite a bit: e.g. what if we change entirely how we handle health in the game?
 * We shouldn't have to change the model
 */
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
    public void onUnload() {
    }

    private void onDeath(DeathEvent event) {
        // <!> never compare strings with ==, always use .equals as some equal strings could potentially have 2 different pointers
        // <!> refactor similar instructions to one method
        boolean isRelevantEvent = isRelevantPlayer(event.getPlayer());

        if (isRelevantEvent) {
            this.currentHealth = this.maxHealth;
        }
    }

    private boolean isRelevantPlayer(IPlayer player) {
        return player.getHealth().getId().equals(this.id);
    }

    private void onEatFood(EatFoodEvent event) {
        // <!> never compare strings with ==, always use .equals as some equal strings could potentially have 2 different pointers
        boolean isRelevantEvent = isRelevantPlayer(event.getPlayer());

        if (isRelevantEvent) {
            // Here we max out. We could refactor this to its own method if we come to re-use this check somewhere else
            this.currentHealth = Math.min(this.maxHealth, this.currentHealth + event.getFood().getHealAmount());
        }
    }

    private void onTakeDamage(TakeDamageEvent event) {
        // <!> never compare strings with ==, always use .equals as some equal strings could potentially have 2 different pointers
        boolean isRelevantEvent = isRelevantPlayer(event.getPlayer());

        if (isRelevantEvent) {
            this.currentHealth -= event.getDamageAmount();
            // This method could be unwrapped here really, doesn't need to be in a new method with this change
            ensurePlayerDeath(event.getPlayer());
        }
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

    public String getId() {
        return this.id;
    }

    // Use a better name for this method, here this method does more than a check, it also kills the player
    // Not sure about a new name, maybe something like this with a refacor
    // This method also checks for too much health `this.currentHealth = Math.min(this.currentHealth, this.maxHealth);`
    // It should be divided into two methods. one for taking damage one for gaining health as both have 2 different behaviours
    private void ensurePlayerDeath(IPlayer player) {
        // <!> never compare strings with ==, always use .equals as some equal strings could potentially have 2 different pointers
        boolean isRelevantEvent = isRelevantPlayer(player);

        if (isRelevantEvent && this.currentHealth <= 0) {
            send(new DeathEvent(player));
        }
    }
}
