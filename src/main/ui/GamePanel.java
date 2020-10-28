package ui;

import model.Troop;
import model.Warrior;
import model.World;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represent a console gaming panel
public class GamePanel {
    private static final String JSON_STORE = "./data/world.json";
    private Scanner input;
    private World world;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initialize the world and open the main menu
    public GamePanel() {
        input = new Scanner(System.in);
        world = new World();
        initElf(world);
        initUndead(world);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        selectFromMenu();
    }

    // MODIFIES: world
    // EFFECTS: initialize an elf troop
    public static World initElf(World world) {
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
        return world;
    }

    // MODIFIES: world
    // EFFECTS: initialize an undead troop
    public static World initUndead(World world) {
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
        return world;
    }

    // MODIFIES: this
    // EFFECTS: display the main menu and process user input
    private void selectFromMenu() {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nMain menu:");
            System.out.println("\tt -> troops");
            System.out.println("\ts -> save game");
            System.out.println("\tl -> load game");
            System.out.println("\tq -> quit");
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            if (keyIn.equals("t")) {
                editTroops();
            } else if (keyIn.equals("s")) {
                saveWorld();
            } else if (keyIn.equals("l")) {
                loadWorld();
            } else if (keyIn.equals("q")) {
                quit = true;
            } else {
                System.out.println("Selection not valid...");
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: display the edit troop menu and process user input
    private void editTroops() {
        boolean quit = false;

        while (!quit) {
            System.out.println("\nSelect from:");
            System.out.println("\te -> edit troops");
            System.out.println("\tl -> list troops");
            System.out.println("\tq -> quit");
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            switch (keyIn) {
                case "e":
                    selectTroops();
                    break;
                case "l":
                    listTroops();
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a troop to edit
    private void selectTroops() {
        int selection;
        boolean quit = false;
        int index;

        while (!quit) {
            index = 0;
            System.out.println("Troops available:\n");
            for (; index < world.getWorldSize(); index++) {
                System.out.println("\t" + (index + 1) + " -> " + world.getTroopByIndex(index).getRace());
            }
            System.out.println("index of troop you want to edit:\n");
            selection = input.nextInt();
            if (selection > 0 && selection <= world.getWorldSize()) {
                quit = true;
                editTroop(world.getTroopByIndex(selection - 1));
            }
            if (!quit) {
                System.out.println("Invalid troop index...\n");
            }
        }
    }

    // EFFECTS: list all troops in world and process user input
    private void listTroops() {
        String selection;
        boolean quit = false;

        System.out.println("Troops available:\n");
        for (int index = 0; index < world.getWorldSize(); index++) {
            System.out.println("\t" + world.getTroopByIndex(index).getRace());
        }
        while (!quit) {
            System.out.println("press \"q\" to quit.\n");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("q")) {
                quit = true;
            } else {
                System.out.println("Selection not valid...\n");
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: prompts user to select operation on the troop being editing
    private void editTroop(Troop troop) {
        boolean quit = false;

        while (!quit) {
            displayEditingTroop(troop);
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            switch (keyIn) {
                case "a":
                    addWarrior(troop);
                    break;
                case "e":
                    selectWarrior(troop);
                    break;
                case "l":
                    listWarriors(troop);
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    // EFFECTS: displays operations to the troop being editing
    private void displayEditingTroop(Troop troop) {
        System.out.println("\nEditing: " + troop.getRace());
        System.out.println("\ta -> add a " + troop.getRace() + " warrior");
        System.out.println("\te -> edit " + troop.getRace() + " warriors");
        System.out.println("\tl -> list " + troop.getRace() + " warriors");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a warrior to edit
    private void selectWarrior(Troop troop) {
        int selection;
        boolean quit = false;
        int index;

        while (!quit) {
            index = 0;
            System.out.println("Warriors available:\n");
            for (; index < troop.getTroopSize(); index++) {
                System.out.println("\t" + (index + 1) + " -> " + troop.getWarriorByIndex(index).getName());
            }
            System.out.println("index of the warrior you want to edit:\n");
            selection = input.nextInt();
            if (selection > 0 && selection <= troop.getTroopSize()) {
                quit = true;
                editWarrior(troop.getWarriorByIndex(selection - 1));
            }
            if (!quit) {
                System.out.println("Invalid warrior index...\n");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select operation on the warrior being editing
    private void editWarrior(Warrior warrior) {
        boolean quit = false;

        while (!quit) {
            displayEditingWarrior(warrior);
            String keyIn = input.next();
            keyIn = keyIn.toLowerCase();
            switch (keyIn) {
                case "r":
                    renameWarrior(warrior);
                    break;
                case "a":
                    editWarriorAttack(warrior);
                    break;
                case "d":
                    editWarriorDefense(warrior);
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("Selection not valid...");
                    break;
            }
        }
    }

    // MODIFIES: this, troop
    // EFFECTS: add a warrior to the given troop
    private void addWarrior(Troop troop) {
        Warrior newWarrior;
        String name;

        System.out.println("New warrior's name:");
        name = input.next();
        newWarrior = new Warrior(name);
        troop.addWarrior(newWarrior);
        System.out.println("Added a " + newWarrior.getName() + " to " + troop.getRace());
        editWarriorAttack(newWarrior);
        editWarriorDefense(newWarrior);
    }

    // EFFECTS: list all warriors in the given troop and process user input
    private void listWarriors(Troop troop) {
        String selection;
        boolean quit = false;

        System.out.println("Warriors available:\n");
        for (int index = 0; index < troop.getTroopSize(); index++) {
            System.out.println("\t" + troop.getWarriorByIndex(index).getName());
        }
        while (!quit) {
            System.out.println("press \"q\" to quit.\n");
            selection = input.next();
            selection = selection.toLowerCase();
            if (selection.equals("q")) {
                quit = true;
            } else {
                System.out.println("Selection not valid...\n");
            }
        }
    }

    // MODIFIES: this, warrior
    // EFFECTS: prompts user to rename the given warrior
    private void renameWarrior(Warrior warrior) {
        System.out.println("Warrior's new name: ");
        String selection = input.next();
        warrior.rename(selection);
        System.out.println("Warrior renamed to " + warrior.getName());
    }

    // MODIFIES: this, warrior
    // EFFECTS: prompts user to edit attack of the given warrior
    private void editWarriorAttack(Warrior warrior) {
        boolean quit = false;
        int attack;

        while (!quit) {
            System.out.println("Warrior's attack: ");
            attack = input.nextInt();
            if (attack > 0) {
                warrior.setAttack(attack);
                quit = true;
            } else {
                System.out.println("enter a positive value\n");
            }
        }
        System.out.println("Warrior's attack changed to " + warrior.getAttack());
    }

    // MODIFIES: this, warrior
    // EFFECTS: prompts user to edit defense of the given warrior
    private void editWarriorDefense(Warrior warrior) {
        boolean quit = false;
        int defense;

        while (!quit) {
            System.out.println("Warrior's defense: ");
            defense = input.nextInt();
            if (defense > 0) {
                warrior.setDefense(defense);
                quit = true;
            } else {
                System.out.println("enter a positive value\n");
            }
        }
        System.out.println("Warrior's defense changed to " + warrior.getDefense());
    }

    // EFFECTS: displays operations to the warrior being editing
    private void displayEditingWarrior(Warrior warrior) {
        System.out.println("\nEditing: " + warrior.getName());
        System.out.println("\tattack: " + warrior.getAttack());
        System.out.println("\tdefense: " + warrior.getDefense());
        System.out.println("\n\tr -> rename the warrior");
        System.out.println("\ta -> edit attack ");
        System.out.println("\td -> edit defense ");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: saves the game world to file
    private void saveWorld() {
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
    private void loadWorld() {
        try {
            world = jsonReader.read();
            System.out.println("Loaded game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
