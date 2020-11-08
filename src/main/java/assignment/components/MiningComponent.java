package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.IPlayer;
import net.gameslabs.events.GatherEvent;
import net.gameslabs.events.GiveExpToPlayerEvent;
import net.gameslabs.events.PickupItemEvent;
import net.gameslabs.model.IGatherable;
import net.gameslabs.model.Item;
import net.gameslabs.model.Skills;

public class MiningComponent extends Component {
    @Override
    public void onLoad() {
        registerEvent(GatherEvent.class, this::onGather);
    }

    @Override
    public void onUnload() {
    }

    private void onGather(GatherEvent event) {
        boolean isRelevantEvent = event.getItem().getRequiredSkill() == Skills.MINING;

        if (isRelevantEvent) {
            int playerLevel = event.getPlayer().getStats().getLevel(event.getItem().getRequiredSkill());

            boolean hasRequiredMiningLevel = playerLevel >= event.getItem().getRequiredLevel();
            if (hasRequiredMiningLevel) {
                gatherItem(event.getPlayer(), event.getItem());
            } else {
                event.setCancelled(true);
            }
        }
    }

    private void gatherItem(IPlayer player, IGatherable item) {
        //Give the player items and exp
        send(new PickupItemEvent(player.getInventory().getId(), (Item)item, item.getGatherAmount()));
        send(new GiveExpToPlayerEvent(player, item.getRequiredSkill(), item.getExperience()));
    }
}
