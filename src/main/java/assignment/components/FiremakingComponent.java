package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.*;
import net.gameslabs.model.Logs;
import net.gameslabs.model.Skill;

public class FiremakingComponent extends Component {
    @Override
    public void onLoad() {
        registerEvent(FiremakingEvent.class, this::onLogsBurn);
    }

    private void onLogsBurn(FiremakingEvent event) {
        Player player = event.getPlayer();
        Logs logs = event.getLogs();
        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(player, Skill.FIREMAKING);

        send(getPlayerLevel);

        if (getPlayerLevel.getLevel() < logs.getRequiredFiremakingLevel()) {
            event.setCancelled(true);
            return;
        }

        send(new GiveXpEvent(player, Skill.FIREMAKING, logs.getFiremakingExperienceGranted()));
        send(new RemoveItemEvent(player, logs.getItem(), 1));
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
