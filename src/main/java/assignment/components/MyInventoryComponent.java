package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.Items.GiveItemEvent;
import net.gameslabs.events.Items.HasItemEvent;
import net.gameslabs.events.Items.RemoveItemEvent;
import net.gameslabs.model.Item;

import java.util.List;

public class MyInventoryComponent extends Component {

    // How do we best manage whether a slot is empty?
    // Official runescape client uses -1 for id when a slot is empty, but never
    // liked this approach, feels lazy, but null items aren't really any better
    private List<Item> inventory;

    @Override
    public void onLoad() {
        registerEvent(GiveItemEvent.class, this::giveItemHandler);
        registerEvent(HasItemEvent.class, this::hasItemHandler);
        registerEvent(RemoveItemEvent.class, this::removeItemHandler);
    }

    private void giveItemHandler(GiveItemEvent event) {

    }

    private void hasItemHandler(HasItemEvent event) {

    }

    private void removeItemHandler(RemoveItemEvent event) {

    }

    private void hasItem(int id, int amount) {

    }

    @Override
    public void onUnload() {

    }
}
