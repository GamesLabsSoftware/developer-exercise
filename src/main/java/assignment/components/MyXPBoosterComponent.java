package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveXpEvent;

public class MyXPBoosterComponent extends Component {

    @Override
    public void onLoad() {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    private void onGiveXP(GiveXpEvent event) {
        // TODO: complete me
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
