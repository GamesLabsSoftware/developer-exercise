package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.api.Player;
import net.gameslabs.events.AddItemEvent;
import net.gameslabs.events.GetPlayerInventoryEvent;
import net.gameslabs.events.RemoveItemEvent;
import net.gameslabs.model.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryComponent extends Component {
    Map<Player, PlayerInventory> state;

    @Override
    public void onLoad() {
        state = new HashMap<>();

        registerEvent(AddItemEvent.class, this::onAddItem);
        registerEvent(GetPlayerInventoryEvent.class, this::getPlayerInventoryEvent);
        registerEvent(RemoveItemEvent.class, this::onRemoveItem);
    }

    private PlayerInventory getPlayerInventory(Player player) {
        return state.computeIfAbsent(player, i -> new PlayerInventory());
    }

    private void getPlayerInventoryEvent(GetPlayerInventoryEvent event) {
        event.setPlayerInventory(getPlayerInventory(event.getPlayer()));
    }

    private void onAddItem(AddItemEvent event) {
        getPlayerInventory(event.getPlayer()).addItem(event.getItem(), event.getQuantity());
    }

    private void onRemoveItem(RemoveItemEvent event) {
        PlayerInventory playerInventory = getPlayerInventory(event.getPlayer());

        if (playerInventory.contains(event.getItem()))
            playerInventory.removeItem(event.getItem(), event.getQuantity());
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}
