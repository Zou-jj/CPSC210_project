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

    public Warrior getWarriorByIndex(int index) {
        return warriors.get(index);
    }

    public void addWarrior(Warrior w) {
        warriors.add(w);
    }

}
