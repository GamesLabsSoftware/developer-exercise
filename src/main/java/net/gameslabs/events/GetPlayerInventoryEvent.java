package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.PlayerInventory;

/**
 * Event representing the retrieval of a Player's inventory.
 */
public class GetPlayerInventoryEvent extends PlayerEvent {
    private PlayerInventory playerInventory;

    /**
     * GetPlayerInventoryEvent constructor with Player context.
     *
     * @param player Player to which the GetPlayerInventoryEvent is scoped.
     */
    public GetPlayerInventoryEvent(Player player) {
        super(player);
    }

    /**
     * Returns the inventory of the scoped Player.
     *
     * @return PlayerInventory of the scoped Player.
     */
    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    /**
     * Sets the scoped Player's inventory to a new PlayerInventory value.
     *
     * @param playerInventory PlayerInventory value to which the scoped Player's inventory will be set.
     */
    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }
}
