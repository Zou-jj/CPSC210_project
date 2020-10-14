package model;

import model.Troop;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World {
    private ArrayList<Troop> races;

    public World() {
        races = new ArrayList<>();
        initElf();
        initUndead();
    }

    // MODIFIES: this
    // EFFECTS: initialize an elf troop
    public void initElf() {
        Troop elf = new Troop("elf");
        Warrior elfArcher = new Warrior("elf archer");
        elfArcher.setAttack(100);
        elfArcher.setDefense(20);
        elf.addWarrior(elfArcher);
        Warrior elfRanger = new Warrior("elf ranger");
        elfRanger.setAttack(70);
        elfRanger.setDefense(50);
        elf.addWarrior(elfRanger);
        races.add(elf);
    }

    // MODIFIES: this
    // EFFECTS: initialize an undead troop
    public void initUndead() {
        Troop undead = new Troop("undead");
        Warrior undeadRider = new Warrior("undead rider");
        undeadRider.setAttack(50);
        undeadRider.setDefense(80);
        undead.addWarrior(undeadRider);
        Warrior undeadBat = new Warrior("undead bat");
        undeadBat.setAttack(100);
        undeadBat.setDefense(10);
        undead.addWarrior(undeadBat);
        races.add(undead);
    }

    public int getWorldSize() {
        return races.size();
    }

    // REQUIRES: index >= 0 and index < getWorldSize
    // EFFECTS: return the troop of given index in world
    public Troop getTroopByIndex(int index) {
        return races.get(index);
    }
}