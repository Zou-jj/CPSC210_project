package model;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private boolean isGameOver;
    public static World world;

    public static final String JSON_STORE = "./data/world.json";
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;

    public Game() {
        world = new World();
        initElf();
        initUndead();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: world
    // EFFECTS: initialize an elf troop
    public static void initElf() {
        Troop elf = new Troop("elf");
        Warrior elfArcher = new Warrior("elf archer");
        elfArcher.setAttack(100);
        elfArcher.setDefense(20);
        elf.addWarrior(elfArcher);
        Warrior elfRanger = new Warrior("elf ranger");
        elfRanger.setAttack(70);
        elfRanger.setDefense(50);
        elf.addWarrior(elfRanger);
        world.addTroop(elf);
    }

    // MODIFIES: world
    // EFFECTS: initialize an undead troop
    public static void initUndead() {
        Troop undead = new Troop("undead");
        Warrior undeadRider = new Warrior("undead rider");
        undeadRider.setAttack(50);
        undeadRider.setDefense(80);
        undead.addWarrior(undeadRider);
        Warrior undeadBat = new Warrior("undead bat");
        undeadBat.setAttack(100);
        undeadBat.setDefense(10);
        undead.addWarrior(undeadBat);
        world.addTroop(undead);
    }

    // EFFECTS: saves the game world to file
    public static void saveWorld() {
        try {
            jsonWriter.open();
            jsonWriter.write(world);
            jsonWriter.close();
            System.out.println("Saved game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game world from file
    public static void loadWorld() {
        try {
            world = jsonReader.read();
            System.out.println("Loaded game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
