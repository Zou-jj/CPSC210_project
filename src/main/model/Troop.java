package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a troop having a collection of warriors
public class Troop implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("race", race);
        json.put("warriors", warriorsToJson());
        return json;
    }

    private JSONArray warriorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Warrior w : warriors) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}

