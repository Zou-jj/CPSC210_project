package model;

public class Warrior {
    private String name;
    private int attack;
    private int defense;

    // REQUIRES: name has a non-zero length
    // EFFECTS: creates a warrior with the name given
    public Warrior(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    // REQUIRES: name has a non-zero length
    // MODIFIES: this
    // EFFECTS: rename the warrior with the name given
    public void rename(String name) {
        this.name = name;
    }

    // REQUIRES: attack >= 0
    // MODIFIES: this
    // EFFECTS: set warrior's attack to given value
    public void setAttack(int attack) {
        this.attack = attack;
    }

    // REQUIRES: defense >= 0
    // MODIFIES: this
    // EFFECTS: set warrior's defense to given value
    public void setDefense(int defense) {
        this.defense = defense;
    }
}
