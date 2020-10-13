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
    void testSetAttack() {
        elfArcher.setAttack(ATTACK);
        assertEquals(ATTACK, elfArcher.getAttack());
    }

    @Test
    void testSetDefense() {
        elfArcher.setDefense(DEFENSE);
        assertEquals(DEFENSE, elfArcher.getDefense());
    }

    @Test
    void testRename() {
        elfArcher.rename(RENAME);
        assertEquals(RENAME, elfArcher.getName());
    }
}