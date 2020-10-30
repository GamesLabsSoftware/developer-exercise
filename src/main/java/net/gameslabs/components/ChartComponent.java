package net.gameslabs.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GetExpForLevelEvent;
import net.gameslabs.events.GiveExpToPlayerEvent;

public class ChartComponent extends Component {
    public static final int XP_STEP = 50;

    public ChartComponent() {
    }

    @Override
    public void onLoad() {
        registerEvent(GetExpForLevelEvent.class, this::onGetXPForLevel);
        registerEvent(GiveExpToPlayerEvent.class, this::onGiveXPToPlayer);
    }

    private void onGetXPForLevel(GetExpForLevelEvent event) {
        event.setExp(event.getLevel() * XP_STEP);
    }

    private void onGiveXPToPlayer(GiveExpToPlayerEvent event) {
        event.getPlayer().getStats().addXp(event.getSkill(), event.getExp());
    }

    @Override
    public void onUnload() {
        // Nothing to do
    }
}
