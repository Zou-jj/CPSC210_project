package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a game world with some troops
public class World implements Writable {
    protected ArrayList<Troop> races;

    public World() {
        races = new ArrayList<>();
    }

    public int getWorldSize() {
        return races.size();
    }

    // REQUIRES: index >= 0 and index < getWorldSize
    // EFFECTS: return the troop of given index in world
    public Troop getTroopByIndex(int index) {
        return races.get(index);
    }

    // MODIFIES: this
    // EFFECTS: add the given warrior to the end of troop
    public void addTroop(Troop t) {
        races.add(t);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("troops", troopsToJson());
        return json;
    }

    private JSONArray troopsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Troop t : races) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
