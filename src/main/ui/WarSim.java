package ui;

import model.Troop;
import model.Warrior;
import model.World;
import persistence.JsonReader;
import persistence.JsonWriter;
import sound.MidiSynth;
import ui.panels.JsonPanel;
import ui.panels.TroopPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// represent a war simulator game
public class WarSim extends JFrame {

    private World world;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final String JSON_STORE = "./data/world.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private MidiSynth midiSynth;

    TroopPanel elfPanel;
    TroopPanel undeadPanel;
    JPanel jsonPanel;

    public WarSim() {
        super("War Simulator");
        initializeFields();
        initializeGraphics();
        initializeSound();
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        world = new World();
        world.addTroop(initElf());
        world.addTroop(initUndead());
        elfPanel = new TroopPanel(world,0);
        undeadPanel = new TroopPanel(world,1);
        jsonPanel = new JsonPanel(this);
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("File", null, jsonPanel, null);
        tabbedPane.addTab("Elf", null, elfPanel, null);
        tabbedPane.addTab("Undead", null, undeadPanel, null);
        add(tabbedPane);
        setContentPane(tabbedPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  initializes this DrawingEditor's midisynth field, then calls open() on it
    private void initializeSound() {
        midiSynth = new MidiSynth();
        midiSynth.open();
//        midiSynth.play(15,100,100);
    }

    // MODIFIES: world
    // EFFECTS: initialize an elf troop
    public Troop initElf() {
        Troop elf = new Troop("elf");
        Warrior elfArcher = new Warrior("elf archer");
        elfArcher.setAttack(100);
        elfArcher.setDefense(20);
        elf.addWarrior(elfArcher);
        Warrior elfRanger = new Warrior("elf ranger");
        elfRanger.setAttack(70);
        elfRanger.setDefense(50);
        elf.addWarrior(elfRanger);
        return elf;
    }

    // MODIFIES: world
    // EFFECTS: initialize an undead troop
    public Troop initUndead() {
        Troop undead = new Troop("undead");
        Warrior undeadRider = new Warrior("undead rider");
        undeadRider.setAttack(50);
        undeadRider.setDefense(80);
        undead.addWarrior(undeadRider);
        Warrior undeadBat = new Warrior("undead bat");
        undeadBat.setAttack(100);
        undeadBat.setDefense(10);
        undead.addWarrior(undeadBat);
        return undead;
    }

    // EFFECTS: saves the game world to file
    public void saveWorld() {
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
    public void loadWorld() {
        try {
            world = jsonReader.read();
            elfPanel.updateTroop(world, 0);
            undeadPanel.updateTroop(world, 1);
            System.out.println("Loaded game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

/*
 *Title:SimpleDrawingPlayer-Complete
 *Author:Norm Hutchinson
 *Date:Oct 19, 2019
 *Availability:https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
 */
