package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.DropItemEvent;
import net.gameslabs.events.PickupItemEvent;
import net.gameslabs.model.InventorySlot;
import net.gameslabs.model.Item;

import java.util.ArrayList;

public class InventoryComponent extends Component {
    private ArrayList<InventorySlot> slots;

    public InventoryComponent(int size) {
        slots = new ArrayList<InventorySlot>(size);
    }

    @Override
    public void onLoad() {
        registerEvent(DropItemEvent.class, this::onDropItem);
        registerEvent(PickupItemEvent.class, this::onPickupItem);
    }

    @Override
    public void onUnload() {
    }

    private void onDropItem(DropItemEvent event) {

    }

    private void onPickupItem(PickupItemEvent event) {

    }

    public InventorySlot tryGetSlotContainingItem(Item item) {
        for (int i = 0; i < slots.size(); i++) {
            InventorySlot slot = slots.get(i);

            if (slot.hasItem(item)) {
                return slot;
            }
        }

        return null;
    }
}