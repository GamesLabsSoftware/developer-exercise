package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IHealth;
import net.gameslabs.api.IInventory;
import net.gameslabs.api.IPlayer;
import net.gameslabs.model.Stats;

import java.util.Objects;

public class PlayerComponent extends Component implements IPlayer {
    private String id;
    private String name;

    private static final int MAXIMUM_HEALTH = 100;
    private static final int NUM_INVENTORY_SLOTS = 37;
    private static final int INVENTORY_SLOT_SIZE = 64;

    private Stats stats;
    private HealthComponent health = new HealthComponent(MAXIMUM_HEALTH);
    private InventoryComponent inventory = new InventoryComponent(NUM_INVENTORY_SLOTS, INVENTORY_SLOT_SIZE);

    private PlayerComponent(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerComponent that = (PlayerComponent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return id + ": " + name + " (Health: " + this.health.getCurrentHealth() + "/" + this.health.getMaxHealth() + ")";
    }

    private static int players;
    public static PlayerComponent createPlayer(String name) {
        return new PlayerComponent("PLAYER-" + (++players), name);
    }

    @Override
    public Stats getStats() {
        if (this.stats == null) {
            this.stats = new Stats();
        }

        return this.stats;
    }

    @Override
    public IInventory getInventory() {
        return this.inventory;
    }

    @Override
    public IHealth getHealth() {
        return this.health;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onUnload() {
    }
}
