package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.Items.GiveItemEvent;
import net.gameslabs.events.Mining.MiningEvent;
import net.gameslabs.events.SkillLevel.GetPlayerLevelEvent;
import net.gameslabs.events.SkillLevel.GiveXpEvent;
import net.gameslabs.model.Rock;
import net.gameslabs.model.SkillLevel.Skill;

public class MiningComponent extends Component {

    @Override
    public void onLoad() {
        registerEvent(MiningEvent.class, this::mineRock);
    }

    private void mineRock(MiningEvent miningEvent) {
        Player player = miningEvent.getPlayer();
        Rock rock = miningEvent.getRock();

        GetPlayerLevelEvent getPlayerLevelEvent = new GetPlayerLevelEvent(player, Skill.MINING);
        send(getPlayerLevelEvent);

        if (getPlayerLevelEvent.getLevel() < rock.getLevelRequired()) {
            miningEvent.setCancelled(true);
            return;
        }

        send(new GiveXpEvent(player, Skill.MINING, rock.getXpRewarded()));
        send(new GiveItemEvent(player, rock.getOreItemId(), 1));
    }

    @Override
    public void onUnload() {

    }
}
