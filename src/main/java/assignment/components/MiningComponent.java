package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.events.MineOreEvent;
import net.gameslabs.events.ObtainItemEvent;
import net.gameslabs.model.Item;
import net.gameslabs.model.Skill;

public class MiningComponent extends Component
{
    @Override
    public void onLoad()
    {
        registerEvent(MineOreEvent.class, this::onMineOreEvent);
    }

    private void onMineOreEvent(MineOreEvent event)
    {
        Player player = event.getPlayer();
        GetPlayerLevel getLvl = new GetPlayerLevel(player, Skill.MINING);

        send(getLvl);

        int mining_level = getLvl.getLevel();

        switch(event.getOre())
        {
            case FOOLS_GOLD:
            {
                send(new GiveXpEvent(player, Skill.MINING, 0)); // NO EXP FOR FOOLS
                send(new ObtainItemEvent(player, Item.REAL_GOLD_ITS_ALL_MINE, 1));
            }break;

            case COAL:
            {
                if(mining_level >= 5)
                {
                    send(new GiveXpEvent(player, Skill.MINING, 25));
                    send(new ObtainItemEvent(player, Item.COAL, 1));
                }
                else
                {
                    event.setCancelled(true);
                }
            }break;

            default: break;
        }
    }

    @Override
    public void onUnload()
    {
    }
}
