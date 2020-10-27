package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWorld {
    private World world;
    private static final int ATTACK = 100;
    private static final int DEFENSE = 20;
    private static final String NAME = "elf archer";
    private static final String RACE = "elf";

    @BeforeEach
    void runBefore() {
        Warrior elfArcher = new Warrior(NAME);
        elfArcher.setAttack(ATTACK);
        elfArcher.setDefense(DEFENSE);
        Troop elf = new Troop(RACE);
        elf.addWarrior(elfArcher);
        world = new World();
        world.addTroop(elf);
    }

    @Test
    void testGetWorldSize() {
        assertEquals(1, world.getWorldSize());
    }

    @Test
    void testGetTroopByIndex() {
        assertEquals(RACE, world.getTroopByIndex(0).getRace());
    }
}
