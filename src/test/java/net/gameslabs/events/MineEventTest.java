package net.gameslabs.events;

import net.gameslabs.api.Player;
import net.gameslabs.implem.PlayerImplem;
import net.gameslabs.model.Ore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MineEventTest {
    private MineEvent sut;

    protected Player player;

    @BeforeEach
    public void setup() throws Exception {
        player = PlayerImplem.newPlayer("Bewgs");
    }

    @Test
    public void MineEventShouldReturnCorrectOre() {
        sut = new MineEvent(player, Ore.IRON);

        assertSame(Ore.IRON, sut.getOre(), "Get ore should return correct ore");
    }

    @Test
    public void MineEventShouldReturnCorrectPlayer() {
        sut = new MineEvent(player, Ore.COPPER);

        assertSame(player, sut.getPlayer(), "Get player should return correct player");
    }
}
