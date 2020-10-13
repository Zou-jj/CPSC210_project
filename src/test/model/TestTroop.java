package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testGetWarriorByIndex() {
        assertEquals(elfArcher, elf.getWarriorByIndex(0));
    }

    @Test
    void testGetTroopSize() {
        assertEquals(1, elf.getTroopSize());
    }
}