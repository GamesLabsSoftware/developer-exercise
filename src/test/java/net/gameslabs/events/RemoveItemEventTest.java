package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.implem.PlayerImplem;
import net.gameslabs.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveItemEventTest {
    private RemoveItemEvent sut;

    protected Player player;

    @BeforeEach
    public void setup() throws Exception {
        player = PlayerImplem.newPlayer("Bewgs");
    }

    @Test
    public void removeItemEventQuantityDefaultsTo1() {
        sut = new RemoveItemEvent(player, Item.LOGS_WILLOW);

        assertEquals(1, sut.getQuantity(), "RemoveItemEvent should have a quantity of 1 if a quantity is not provided");
    }

    @Test
    public void removeItemEventQuantityShouldBeReturnedCorrectly() {
        sut = new RemoveItemEvent(player, Item.COINS, 1200);

        assertEquals(1200, sut.getQuantity(), "RemoveItemEvent quantity should be returned correctly");
    }

    @Test
    public void removeItemEventItemShouldBeReturnedCorrectly() {
        sut = new RemoveItemEvent(player, Item.SHRIMP_BURNT);

        assertEquals(Item.SHRIMP_BURNT, sut.getItem(), "RemoveItemEvent quantity should be returned correctly");
    }

    @Test
    public void removeItemEventShouldReturnCorrectPlayer() {
        sut = new RemoveItemEvent(player, Item.COINS, 50);

        assertSame(player, sut.getPlayer(), "Get player should return correct player");
    }
}
