package net.gameslabs.implem;

import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevelEvent;
import net.gameslabs.model.Skill;

import java.util.Objects;

public class PlayerImplem implements Player {

    private String id;
    private String name;

    private PlayerImplem(String id, String name) {
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
        PlayerImplem that = (PlayerImplem) o;
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
    public static Player newPlayer(String name) {
        return new PlayerImplem("PLAYER-" + (++players), name);
    }

    @Override
    public int getLevel(ComponentRegistry registry, Skill skill) {
        GetPlayerLevelEvent getPlayerLevel = new GetPlayerLevelEvent(this, skill);
        registry.sendEvent(getPlayerLevel);

        return getPlayerLevel.getLevel();
    }
}
