package persistence;

import model.Troop;
import model.Warrior;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {

    protected void checkWarrior(String name,int attack, int defense, Warrior warrior) {
        assertEquals(name, warrior.getName());
        assertEquals(attack, warrior.getAttack());
        assertEquals(defense, warrior.getDefense());
    }
}

/*
 *Title:JsonSerializationDemo
 *Author:Paul Carter
 *Date:Oct 17, 2020
 *Availability:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */