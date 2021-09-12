package net.gameslabs.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerInventoryTest {
    private PlayerInventory sut;

    @BeforeEach
    public void setup() throws Exception {
        sut = new PlayerInventory();
    }

    @Test
    public void playerInventoryAddItemQuantityDefaultsTo1() {
        sut.addItem(Item.ORE_TIN);

        assertEquals(1, sut.getQuantity(Item.ORE_TIN), "Add item default quantity should be 1");
    }

    @Test
    public void playerInventoryAddItemCorrectlyAddsToNullValue() {
        sut.addItem(Item.SHRIMP_BURNT, 10);

        assertEquals(10, sut.getQuantity(Item.SHRIMP_BURNT), "Add new item to inventory should add correctly");
    }

    @Test
    public void playerInventoryAddItemCorrectlyAddsItemQuantities() {
        sut.addItem(Item.LOGS_WILLOW, 5);
        sut.addItem(Item.LOGS_WILLOW, 3);

        assertEquals(8, sut.getQuantity(Item.LOGS_WILLOW), "Add existing item to inventory should add correctly");
    }

    @Test
    public void playerInventoryDeterminesWhetherItemIsContained() {
        sut.addItem(Item.LOGS_STANDARD);
        sut.addItem(Item.LOGS_OAK, 3);
        sut.addItem(Item.SHRIMP_BURNT, 5);
        sut.removeItem(Item.SHRIMP_BURNT, 5);

        assertTrue(sut.contains(Item.LOGS_STANDARD), "Player inventory correctly determines when it contains an item");
        assertTrue(sut.contains(Item.LOGS_OAK), "Player inventory correctly determines when it contains an item");
        assertFalse(sut.contains(Item.COINS), "Player inventory correctly determines when it has never contained an item");
        assertFalse(sut.contains(Item.SHRIMP_BURNT), "Player inventory correctly determines when it does not contain an item anymore");
    }

    @Test
    public void playerInventoryRemoveItemQuantityDefaultsToAll() {
        sut.addItem(Item.ORE_IRON, 4);
        sut.removeItem(Item.ORE_IRON);

        assertEquals(0, sut.getQuantity(Item.ORE_IRON), "Remove item quantity defaults to all if not specified");
    }

    @Test
    public void playerInventoryRemoveItemRemovesCorrectQuantity() {
        sut.addItem(Item.ORE_COPPER, 5);
        sut.removeItem(Item.ORE_COPPER, 3);

        assertEquals(2, sut.getQuantity(Item.ORE_COPPER), "Remove item removes correct quantity");
    }

    @Test
    public void playerInventoryRemoveItemDoesNotAllowNegativeValues() {
        sut.addItem(Item.SHRIMP_BURNT, 6);
        sut.removeItem(Item.SHRIMP_BURNT, 100);

        assertEquals(0, sut.getQuantity(Item.SHRIMP_BURNT), "Remove item does not result in negative quantities");
    }
}
