package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.model.InventorySlot;
import net.gameslabs.model.Item;

import java.util.ArrayList;

public class InventoryComponent extends Component {
    private ArrayList<InventorySlot> slots;

    public InventoryComponent(int size) {
        slots = new ArrayList<InventorySlot>(size);
    }

    public void tryAddItem(Item item) { tryAddItem(item, 1); }

    public void tryAddItem(Item item, int quantity) {
        InventorySlot slotContainingItem = tryGetSlotContainingItem(item);

        boolean slotAlreadyExists = slotContainingItem != null;
        if (slotAlreadyExists) {
        } else {
        }
    }

    public InventorySlot tryGetSlotContainingItem(Item item) {
        for (int i = 0; i < slots.size(); i++) {
            InventorySlot slot = slots.get(i);

            boolean slotContainsItem = slot.getItem().getId() == item.getId();
            if (slotContainsItem) {
                return slot;
            }
        }

        return null;
    }

    @Override
    public void onLoad() {
        //registerEvent(GiveXPToPlayerEvent.class, this::onGiveXP);
    }

    @Override
    public void onUnload() {
    }
}