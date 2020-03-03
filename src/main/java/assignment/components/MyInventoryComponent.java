package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.Items.GiveItemEvent;
import net.gameslabs.events.Items.HasItemEvent;
import net.gameslabs.events.Items.RemoveItemEvent;
import net.gameslabs.model.Items.Inventory;

public class MyInventoryComponent extends Component {

    @Override
    public void onLoad() {
        registerEvent(GiveItemEvent.class, this::giveItem);
        registerEvent(HasItemEvent.class, this::hasItem);
        registerEvent(RemoveItemEvent.class, this::removeItem);
    }

    private void giveItem(GiveItemEvent event) {
        Inventory inventory = event.getPlayer().getInventory();
        if(!inventory.giveItem(event.getId(), event.getAmount())) {
            event.setCancelled(true);
        }
    }

    private void hasItem(HasItemEvent event) {
        Inventory inventory = event.getPlayer().getInventory();
        event.setResult(inventory.hasItem(event.getId(), event.getAmount()));
    }

    private void removeItem(RemoveItemEvent event) {
        Inventory inventory = event.getPlayer().getInventory();
        if (!inventory.removeItem(event.getId(), event.getAmount())) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onUnload() {

    }
}
