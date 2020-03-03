package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.Items.GiveItemEvent;
import net.gameslabs.events.Items.HasItemEvent;
import net.gameslabs.events.Items.RemoveItemEvent;
import net.gameslabs.model.Inventory;
import net.gameslabs.model.ItemSlot;

import java.util.List;

public class MyInventoryComponent extends Component {

    // How do we best manage whether a slot is empty?
    // Official runescape client uses -1 for id when a slot is empty, but never
    // liked this approach, feels lazy, but null items aren't really any better
    private List<ItemSlot> inventory;

    @Override
    public void onLoad() {
        registerEvent(GiveItemEvent.class, this::giveItem);
        registerEvent(HasItemEvent.class, this::hasItem);
        registerEvent(RemoveItemEvent.class, this::removeItem);
    }

    private void giveItem(GiveItemEvent event) {
        Inventory inventory = event.getPlayer().getInventory();
        event.setResult(inventory.giveItem(event.getId(), event.getAmount()));
    }

    private void hasItem(HasItemEvent event) {
        Inventory inventory = event.getPlayer().getInventory();
        event.setResult(inventory.hasItem(event.getId(), event.getAmount()));
    }

    private void removeItem(RemoveItemEvent event) {
        Inventory inventory = event.getPlayer().getInventory();
        event.setResult(inventory.removeItem(event.getId(), event.getAmount()));
    }

    @Override
    public void onUnload() {

    }
}
