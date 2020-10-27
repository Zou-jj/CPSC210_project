package persistence;

import model.Warrior;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {
    protected void checkWarrior(String name,int attack, int defense, Warrior warrior) {
        assertEquals(name, warrior.getName());
        assertEquals(attack, warrior.getAttack());
        assertEquals(defense, warrior.getDefense());
    }
}
