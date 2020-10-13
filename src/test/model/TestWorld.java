package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWorld {
    private World world;
    private String RACE = "elf";

    @BeforeEach
    void runBefore() {
        world = new World();
    }

    @Test
    void testGetWorldSize() {
        assertEquals(2, world.getWorldSize());
    }

    @Test
    void testGetTroopByIndex() {
        assertEquals(RACE, world.getTroopByIndex(0).getRace());
    }
}
