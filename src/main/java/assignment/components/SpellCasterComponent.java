package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.ConsumeItemEvent;
import net.gameslabs.events.GetInventoryEvent;
import net.gameslabs.events.SpellCastEvent;
import net.gameslabs.model.Inventory;
import net.gameslabs.model.Item;

public class SpellCasterComponent extends Component
{
    @Override
    public void onLoad()
    {
        registerEvent(SpellCastEvent.class, this::onSpellCast);
    }

    private void onSpellCast(SpellCastEvent event)
    {
        Player player = event.getPlayer();
        
        GetInventoryEvent invEvent = new GetInventoryEvent(player);
        
        send(invEvent);
        
        Inventory inv = invEvent.getInventory();
       
        switch(event.getSpell())
        {
            case FIREBALL:
            {
                if(inv.contains(Item.FIREBALL_SCROLL))
                {
                    System.out.println("FIREBALL!");
                    send(new ConsumeItemEvent(player, Item.FIREBALL_SCROLL, 1));
                }
            }break;

            case MAGIC_MISSILE:
            {
                if(inv.contains(Item.MAGIC_MISSILE_SCROLL))
                {
                    System.out.println("MAGIC MISSILE!");
                    send(new ConsumeItemEvent(player, Item.MAGIC_MISSILE_SCROLL,1));
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
