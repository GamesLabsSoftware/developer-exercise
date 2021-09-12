package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;
import net.gameslabs.model.Item;

/**
 * Event representing the addition of an Item to a Player's inventory.
 */
public class AddItemEvent extends PlayerEvent {
    private final Item item;
    private final int quantity;

    /**
     * AddItemEvent constructor with Player context, Item, and default quantity.
     *
     * @param player Player to which the AddItemEvent is scoped.
     * @param item   Item to be added.
     */
    public AddItemEvent(Player player, Item item) {
        super(player);
        this.item = item;
        this.quantity = 1;
    }

    /**
     * AddItemEvent constructor with Player context, Item, and default quantity.
     *
     * @param player   Player to which the AddItemEvent is scoped.
     * @param item     Item to be added.
     * @param quantity Quantity of the specified Item to be added.
     */
    public AddItemEvent(Player player, Item item, int quantity) {
        super(player);
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Returns the Item specified in the AddItemEvent.
     *
     * @return Item specified in the AddItemEvent.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the Item quantity specified in the AddItemEvent.
     *
     * @return Item quantity specified in the AddItemEvent.
     */
    public int getQuantity() {
        return quantity;
    }
}
