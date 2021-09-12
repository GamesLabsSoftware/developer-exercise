package net.gameslabs.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event sut;

    @Test
    public void eventCancelledStateDefaultsToFalse() {
        sut = new Event();

        assertFalse(sut.isCancelled(), "Event cancelled state should default to false");
    }

    @Test
    public void eventReturnsCorrectCancelledState() {
        sut = new Event();

        sut.setCancelled(true);
        assertTrue(sut.isCancelled(), "Event cancelled state should correctly set to true from false");

        sut.setCancelled(false);
        assertFalse(sut.isCancelled(), "Event cancelled state should correctly set to false from true");
    }
}
