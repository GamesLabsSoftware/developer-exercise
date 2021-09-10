package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Item;

/**
 * Event representing the removal of an Item from a Player's inventory.
 */
public class RemoveItemEvent extends PlayerEvent {
    private Item item;
    private int quantity;

    /**
     * RemoveItemEvent constructor with Player scope, Item, and default quantity.
     *
     * @param player Player to which the RemoveItemEvent is scoped.
     * @param item   Item to be removed.
     */
    public RemoveItemEvent(Player player, Item item) {
        super(player);
        this.item = item;
        this.quantity = 1;
    }

    /**
     * RemoveItemEvent constructor with Player scope, Item, and quantity.
     *
     * @param player   Player to which the RemoveItemEvent is scoped.
     * @param item     Item to be removed.
     * @param quantity Quantity of the specified Item to be removed.
     */
    public RemoveItemEvent(Player player, Item item, int quantity) {
        super(player);
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Returns the Item specified in the RemoveItemEvent.
     *
     * @return Item specified in the RemoveItemEvent.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the Item quantity specified in the RemoveItemEvent.
     *
     * @return Item quantity specified in the RemoveItemEvent.
     */
    public int getQuantity() {
        return quantity;
    }
}
