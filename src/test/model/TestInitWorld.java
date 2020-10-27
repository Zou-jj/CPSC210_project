package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInitWorld {
    private InitWorld initWorld;
    private String RACE = "elf";

    @BeforeEach
    void runBefore() {
        initWorld = new InitWorld();
    }

    @Test
    void testGetWorldSize() {
        assertEquals(2, initWorld.getWorldSize());
    }

    @Test
    void testGetTroopByIndex() {
        assertEquals(RACE, initWorld.getTroopByIndex(0).getRace());
    }
}
