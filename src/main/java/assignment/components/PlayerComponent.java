package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevelEvent;
import net.gameslabs.model.Stats;
import net.gameslabs.model.Skills;

import java.util.Objects;

public class PlayerComponent extends Component implements Player {
    private Stats stats;

    private String id;
    private String name;

    private InventoryComponent inventory = new InventoryComponent(37);

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
        return id + ": " + name;
    }

    private static int players;
    public static PlayerComponent createPlayer(String name) {
        return new PlayerComponent("PLAYER-" + (++players), name);
    }

    @Override
    public int getLevel(ComponentRegistry registry, Skills skill) {
        GetPlayerLevelEvent getPlayerLevel = new GetPlayerLevelEvent(this, skill);
        registry.sendEvent(getPlayerLevel);

        return getPlayerLevel.getLevel();
    }

    @Override
    public Stats getStats() {
        if (this.stats == null) {
            this.stats = new Stats();
        }

        return this.stats;
    }

    @Override
    public InventoryComponent getInventory() {
        return this.inventory;
    }

    @Override
    public void onLoad() {
    }

    @Override
    public void onUnload() {
    }
}
