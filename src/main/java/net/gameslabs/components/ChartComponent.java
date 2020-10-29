package net.gameslabs.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevelEvent;
import net.gameslabs.events.GetXPForLevelEvent;
import net.gameslabs.events.GiveXPToPlayerEvent;
import net.gameslabs.model.PlayerStats;

import java.util.HashMap;
import java.util.Map;

public class ChartComponent extends Component {
    private static final int XP_STEP = 50;

    private Map<Player, PlayerStats> persistence;

    public ChartComponent() {
        persistence = new HashMap<>();
    }

    @Override
    public void onLoad() {
        registerEvent(GetXPForLevelEvent.class, this::onGetXPForLevel);
        registerEvent(GiveXPToPlayerEvent.class, this::onGiveXPToPlayer);
        registerEvent(GetPlayerLevelEvent.class, this::onGetPlayerLevel);
    }

    private void onGetXPForLevel(GetXPForLevelEvent event) {
        event.setXp(event.getLevel() * XP_STEP);
    }

    private void onGiveXPToPlayer(GiveXPToPlayerEvent event) {
        getStats(event.getPlayer()).addXp(event.getSkill(), event.getXp());
    }

    private void onGetPlayerLevel(GetPlayerLevelEvent event) {
        int skillXp = getStats(event.getPlayer()).getXp(event.getSkill());
        event.setLevel(getLevelFromXp(skillXp));
    }

    private int getLevelFromXp(int xp) {
        return 1 + Math.floorDiv(xp, XP_STEP);
    }

    private PlayerStats getStats(Player player) {
        return persistence.computeIfAbsent(player, p -> new PlayerStats());
    }

    @Override
    public void onUnload() {
        // Nothing to do
    }
}
