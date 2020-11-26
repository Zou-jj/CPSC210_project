package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestTroop {
    private Warrior elfArcher;
    private static final int ATTACK = 100;
    private static final int DEFENSE = 20;
    private static final String NAME = "elf archer";
    private static final String RACE = "elf";
    private Troop elf;

    @BeforeEach
    void runBefore() {
        elfArcher = new Warrior(NAME);
        elfArcher.setAttack(ATTACK);
        elfArcher.setDefense(DEFENSE);
        elf = new Troop(RACE);
        elf.addWarrior(elfArcher);
    }

    @Test
    void testGetRace() {
        assertEquals(RACE, elf.getRace());
    }

    @Test
    void testGetWarriorByName() {
        assertEquals(elfArcher, elf.getWarriorByName(NAME));
    }

    @Test
    void testContainsName() {
        assertTrue(elf.containsName(NAME));
        assertFalse(elf.containsName(""));
    }

    @Test
    void testGetTroopSize() {
        assertEquals(1, elf.getTroopSize());
    }
}