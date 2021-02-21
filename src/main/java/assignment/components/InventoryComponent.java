package assignment.components;

import java.util.HashMap;
import java.util.Map;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.ConsumeItemEvent;
import net.gameslabs.events.GetInventoryEvent;
import net.gameslabs.events.ObtainItemEvent;
import net.gameslabs.model.Inventory;
import net.gameslabs.model.Item;

public class InventoryComponent extends Component
{
    Map<Player, Inventory> persistance;

    @Override 
    public void onLoad()
    {
        persistance = new HashMap<>();

        registerEvent(GetInventoryEvent.class, this::getInventoryEvent);
        registerEvent(ObtainItemEvent.class, this::onObtainItem);
        registerEvent(ConsumeItemEvent.class, this::onConsumeItem);
    }

    private Inventory getInventory(Player player) { return persistance.computeIfAbsent(player, i -> new Inventory()); }

    private void getInventoryEvent(GetInventoryEvent event)
    {
        event.setInventory(getInventory(event.getPlayer()));
    }

    private void onConsumeItem(ConsumeItemEvent event)
    {		
        Inventory inv = getInventory(event.getPlayer());
		
		Item item = event.getItem();
		
		if(inv.contains(event.getItem()))
		{
			int value_after_use = inv.getAmount(item) - event.getAmount();
			
			if(value_after_use < 0 )
            {
				event.setCancelled(true); // Not enough to consume what is required.
                return;
            }

			inv.removeItem(item);
		}
    }
    
    private void onObtainItem(ObtainItemEvent event)
    {
        Inventory inv = getInventory(event.getPlayer());

        inv.addItem(event.getItem(), event.getAmount()); 
    }

    @Override 
    public void onUnload()
    {

    }
}
