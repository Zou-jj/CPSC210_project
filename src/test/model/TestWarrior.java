package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestWarrior {
    private static final int ATTACK = 100;
    private static final int DEFENSE = 20;
    private static final String NAME = "elf archer";
    private static final String RENAME = "elf rider";
    private Warrior elfArcher;

    @BeforeEach
    void runBefore() {
        elfArcher = new Warrior("elf archer");
    }

    @Test
    void testSetPositiveAttack() {
        elfArcher.setAttack(ATTACK);
        assertEquals(ATTACK, elfArcher.getAttack());
    }

    @Test
    void testSetNegativeAttack() {
        elfArcher.setAttack(-1);
        assertEquals(0, elfArcher.getAttack());
    }

    @Test
    void testSetPositiveDefense() {
        elfArcher.setDefense(DEFENSE);
        assertEquals(DEFENSE, elfArcher.getDefense());
    }

    @Test
    void testSetNegativeDefense() {
        elfArcher.setDefense(-1);
        assertEquals(0, elfArcher.getDefense());
    }

    @Test
    void testRename() {
        elfArcher.rename(RENAME);
        assertEquals(RENAME, elfArcher.getName());
    }

    @Test
    void testEquals() {
        Warrior otherElfArcher = new Warrior("elf archer");
        assertEquals(otherElfArcher, elfArcher);
        assertEquals(otherElfArcher.hashCode(), elfArcher.hashCode());
    }

    @Test
    void testRenameEquals() {
        Warrior warrior = new Warrior(RENAME);
        elfArcher.rename(RENAME);
        assertEquals(warrior, elfArcher);
    }

    @Test
    void testNull() {
        Warrior warrior = null;
        assertFalse(elfArcher.equals(warrior));
    }

    @Test
    void testSame() {
        Warrior same = elfArcher;
        assertEquals(same, elfArcher);
    }
}