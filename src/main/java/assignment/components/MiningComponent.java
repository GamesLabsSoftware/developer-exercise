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
        //Verify we are dealing with the mining skill
        if (event.getItem().getRequiredSkill() == Skills.MINING) {
            int playerLevel = event.getPlayer().getStats().getLevel(event.getItem().getRequiredSkill());

            //Don't have required mining level
            if (playerLevel < event.getItem().getRequiredLevel()) {
                event.setCancelled(true);
                return;
            }

            gatherItem(event.getPlayer(), event.getItem());
        }
    }

    private void gatherItem(IPlayer player, IGatherable item) {
        //Give the player items and exp
        send(new PickupItemEvent((Item)item, item.getGatherAmount()));
        send(new GiveExpToPlayerEvent(player, item.getRequiredSkill(), item.getExperience()));
    }
}
