package ui;

import model.Game;
import model.Troop;
import model.World;
import sound.MidiSynth;
import ui.panels.JsonPanel;
import ui.panels.TroopPanel;

import javax.swing.*;
import java.awt.*;

// represent a war simulator game
public class WarSim extends JFrame {
    private Troop elf;
    private Troop undead;
    private Game game;
    private World world;

    private MidiSynth midiSynth;

    JPanel elfPanel;
    JPanel undeadPanel;
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
        game = new Game();
        world = game.getWorld();
        elf = world.getTroopByIndex(0);
        elfPanel = new TroopPanel(elf);
        undead = world.getTroopByIndex(1);
        undeadPanel = new TroopPanel(undead);
        jsonPanel = new JsonPanel();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));

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
}

/*
 *Title:SimpleDrawingPlayer-Complete
 *Author:Norm Hutchinson
 *Date:Oct 19, 2019
 *Availability:https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
 */
