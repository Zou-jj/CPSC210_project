package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Troop;
import model.Warrior;
import model.World;
import org.json.*;

// Represents a reader that reads world from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads world from file and returns it;
    // throws IOException if an error occurs reading data from file
    public World read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorld(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses world from JSON object and returns it
    private World parseWorld(JSONObject jsonObject) {
        World w = new World();
        addTroops(w, jsonObject);
        return w;
    }

    // MODIFIES: w
    // EFFECTS: parses troops from JSON object and adds them to world
    private void addTroops(World w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("troops");
        for (Object json : jsonArray) {
            JSONObject nextTroop = (JSONObject) json;
            w.addTroop(parseTroop(nextTroop));
        }
    }

    // EFFECTS: parses troop from JSON object and returns it
    private Troop parseTroop(JSONObject jsonObject) {
        String race = jsonObject.getString("race");
        JSONArray jsonArray = jsonObject.getJSONArray("warriors");
        Troop t = new Troop(race);
        addWarriors(t, jsonArray);
        return t;
    }

    // MODIFIES: t
    // EFFECTS: parses troop from JSON object and adds warriors to a troop
    private void addWarriors(Troop t, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject nextWarrior = (JSONObject) json;
            t.addWarrior(parseWarrior(nextWarrior));
        }
    }

    // EFFECTS: parses warrior from JSON object and returns it
    private Warrior parseWarrior(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int attack = jsonObject.getInt("attack");
        int defense = jsonObject.getInt("defense");
        Warrior warrior = new Warrior(name);
        warrior.setAttack(attack);
        warrior.setDefense(defense);
        return warrior;
    }
}

/*
 *Title:JsonSerializationDemo
 *Author:Paul Carter
 *Date:Oct 17, 2020
 *Availability:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */