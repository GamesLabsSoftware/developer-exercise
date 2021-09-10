package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.AddItemEvent;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.events.MineEvent;
import net.gameslabs.model.Ore;
import net.gameslabs.model.Skill;

public class MiningComponent extends Component {
    @Override
    public void onLoad() {
        registerEvent(MineEvent.class, this::onMineEvent);
    }

    private void onMineEvent(MineEvent event) {
        Player player = event.getPlayer();
        Ore ore = event.getOre();
        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(player, Skill.MINING);

        send(getPlayerLevel);

        if (getPlayerLevel.getLevel() < ore.getRequiredMiningLevel()) {
            event.setCancelled(true);
            return;
        }

        send(new GiveXpEvent(player, Skill.MINING, ore.getMiningExperienceGranted()));
        send(new AddItemEvent(player, ore.getItem(), 1));
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
