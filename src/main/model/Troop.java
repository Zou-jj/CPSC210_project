package model;

import java.util.ArrayList;

public class Troop {
    private String race;
    private ArrayList<Warrior> warriors;

    public Troop(String race) {
        this.race = race;
        warriors = new ArrayList<>();
    }

    public String getRace() {
        return race;
    }

    public int getTroopSize() {
        return warriors.size();
    }

    // REQUIRES: index >= 0 and index < getTroopSize
    // EFFECTS: return the warrior of given index in troop
    public Warrior getWarriorByIndex(int index) {
        return warriors.get(index);
    }

    // MODIFIES: this
    // EFFECTS: add the given warrior to the end of troop
    public void addWarrior(Warrior w) {
        warriors.add(w);
    }

}
