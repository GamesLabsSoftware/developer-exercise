package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.implem.PlayerImplem;
import net.gameslabs.model.Item;
import net.gameslabs.model.PlayerInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetPlayerInventoryEventTest {
    private GetPlayerInventoryEvent sut;

    protected Player player;
    protected PlayerInventory playerInventory;

    @BeforeEach
    public void setup() throws Exception {
        player = PlayerImplem.newPlayer("Bewgs");
        playerInventory = new PlayerInventory();

        sut = new GetPlayerInventoryEvent(player);
        sut.setPlayerInventory(playerInventory);
    }

    @Test
    public void getPlayerInventoryOnEmptyInventoryShouldReturnEmptyPlayerInventory() {
        PlayerInventory returnedPlayerInventory = sut.getPlayerInventory();

        assertNotNull(returnedPlayerInventory, "Empty player inventory should not be returned as null");
        assertEquals(PlayerInventory.class, returnedPlayerInventory.getClass(), "Empty player inventory should be returned as PlayerInventory object");
    }

    @Test
    public void getPlayerInventoryShouldReturnCorrectInventory() {
        playerInventory.addItem(Item.COINS, 500);
        playerInventory.addItem(Item.ORE_TIN, 12);
        playerInventory.addItem(Item.SHRIMP_BURNT, 4);

        PlayerInventory returnedPlayerInventory = sut.getPlayerInventory();

        assertEquals(500, returnedPlayerInventory.getQuantity(Item.COINS), "Player inventory should return correct item values");
        assertEquals(12, returnedPlayerInventory.getQuantity(Item.ORE_TIN), "Player inventory should return correct item values");
        assertEquals(4, returnedPlayerInventory.getQuantity(Item.SHRIMP_BURNT), "Player inventory should return correct item values");
    }

    @Test
    public void setPlayerInventoryShouldCorrectlySetNewInventoryValue() {
        playerInventory.addItem(Item.COINS, 500);
        playerInventory.addItem(Item.ORE_TIN, 12);
        playerInventory.addItem(Item.SHRIMP_BURNT, 4);

        PlayerInventory newPlayerInventory = new PlayerInventory();
        newPlayerInventory.addItem(Item.LOGS_WILLOW, 10);
        newPlayerInventory.addItem(Item.ORE_COPPER, 2);

        sut.setPlayerInventory(newPlayerInventory);
        PlayerInventory returnedPlayerInventory = sut.getPlayerInventory();

        assertSame(newPlayerInventory, returnedPlayerInventory, "New PlayerInventory should replace any previously set PlayerInventory");
        assertEquals(10, returnedPlayerInventory.getQuantity(Item.LOGS_WILLOW), "Player inventory should return correct item values");

        assertEquals(0, returnedPlayerInventory.getQuantity(Item.COINS), "Player inventory should return correct item values");
        assertEquals(0, returnedPlayerInventory.getQuantity(Item.ORE_TIN), "Player inventory should return correct item values");
        assertEquals(0, returnedPlayerInventory.getQuantity(Item.SHRIMP_BURNT), "Player inventory should return correct item values");
    }
}
