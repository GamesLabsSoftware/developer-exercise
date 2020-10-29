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

    public ChartComponent() {
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
        event.getPlayer().getStats().addXp(event.getSkill(), event.getXp());
    }

    private void onGetPlayerLevel(GetPlayerLevelEvent event) {
        int skillXp = event.getPlayer().getStats().getXp(event.getSkill());
        event.setLevel(getLevelFromXp(skillXp));
    }

    private int getLevelFromXp(int xp) {
        return 1 + Math.floorDiv(xp, XP_STEP);
    }

    @Override
    public void onUnload() {
        // Nothing to do
    }
}
