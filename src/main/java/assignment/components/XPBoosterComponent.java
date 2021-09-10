package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveXpEvent;

public class XPBoosterComponent extends Component {
    @Override
    public void onLoad() {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    private void onGiveXP(GiveXpEvent event) {
        event.setXp(event.getXp() * event.getSkill().getMultiplier());
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
