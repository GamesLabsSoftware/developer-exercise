package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.implem.PlayerImplem;
import net.gameslabs.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddItemEventTest {
    private AddItemEvent sut;

    protected Player player;

    @BeforeEach
    public void setup() throws Exception {
        player = PlayerImplem.newPlayer("Bewgs");
    }

    @Test
    public void addItemEventQuantityDefaultsTo1() {
        sut = new AddItemEvent(player, Item.SHRIMP_BURNT);

        assertEquals(1, sut.getQuantity(), "AddItemEvent should have a quantity of 1 if a quantity is not provided");
    }

    @Test
    public void addItemEventQuantityShouldBeReturnedCorrectly() {
        sut = new AddItemEvent(player, Item.LOGS_WILLOW, 5);

        assertEquals(5, sut.getQuantity(), "AddItemEvent quantity should be returned correctly");
    }

    @Test
    public void addItemEventItemShouldBeReturnedCorrectly() {
        sut = new AddItemEvent(player, Item.ORE_TIN);

        assertEquals(Item.ORE_TIN, sut.getItem(), "AddItemEvent quantity should be returned correctly");
    }

    @Test
    public void addItemEventShouldReturnCorrectPlayer() {
        sut = new AddItemEvent(player, Item.COINS, 50);

        assertSame(player, sut.getPlayer(), "Get player should return correct player");
    }
}
