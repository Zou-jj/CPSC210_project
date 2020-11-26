package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;
import java.util.Map;

// Represents a troop having a collection of warriors
public class Troop implements Writable {
    private String race;
    public Map<String, Warrior> warriorsHashMap;

    public Troop(String race) {
        this.race = race;
        warriorsHashMap = new HashMap<>();
    }

    public String getRace() {
        return race;
    }

    public int getTroopSize() {
        return warriorsHashMap.size();
    }

    // REQUIRES: warrior with the input name is in the map
    // EFFECTS: return the warrior of given index in troop
    public Warrior getWarriorByName(String name) {
        return warriorsHashMap.get(name);
    }

    public boolean containsName(String name) {
        return warriorsHashMap.containsKey(name);
    }

    // MODIFIES: this
    // EFFECTS: add the given warrior to the end of troop
    public void addWarrior(Warrior w) {
        warriorsHashMap.put(w.getName(), w);
    }

    // EFFECTS: add this troop to the json file and returns the json file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("race", race);
        json.put("warriors", warriorsToJson());
        return json;
    }

    // EFFECTS: returns warriors in this troop as a JSON array
    private JSONArray warriorsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Warrior w : warriorsHashMap.values()) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
}

/*
 *Title:JsonSerializationDemo
 *Author:Paul Carter
 *Date:Oct 17, 2020
 *Availability:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

