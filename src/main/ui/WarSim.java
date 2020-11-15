package ui;

import model.Game;
import model.Troop;
import ui.panels.JsonPanel;
import ui.panels.LabelChanger;
import ui.panels.TroopPanel;
import ui.tools.LoadTool;
import ui.tools.SaveTool;
import ui.tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WarSim extends JFrame {

    private List<Tool> tools;
    private Tool activeTool;

    private Game game;
    private Troop elf;
    private Troop undead;

    private JPanel currentPanel;
    JPanel simpleBorders = new JPanel();
    JPanel toolArea = new JPanel();
    JPanel test = new JPanel();
    JPanel elfPanel;
    JPanel undeadPanel;
    JPanel jsonPanel;
    public static LabelChanger ltest = new LabelChanger();

    public WarSim() {
        super("War Simulator");
        initializeFields();
        initializeGraphics();
//        initializeSound();
//        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool, currentDrawing to null, and instantiates drawings and tools with ArrayList
    //           this method is called by the DrawingEditor constructor
    private void initializeFields() {
        activeTool = null;
        currentPanel = null;
        tools = new ArrayList<Tool>();
        game = new Game();
        elf = Game.world.getTroopByIndex(0);
        elfPanel = new TroopPanel(elf);
        undead = Game.world.getTroopByIndex(1);
        undeadPanel = new TroopPanel(undead);
        jsonPanel = new JsonPanel();
    }

    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(Game.WIDTH, Game.HEIGHT));



//        Border paneEdge = BorderFactory.createEmptyBorder(0,10,10,10);
//
//        simpleBorders.setBorder(paneEdge);
//        simpleBorders.setLayout(new BoxLayout(simpleBorders,
//                BoxLayout.Y_AXIS));

        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("File", null, jsonPanel, null);
        tabbedPane.addTab("Elf", null, elfPanel, null);
        tabbedPane.addTab("Undead", null, undeadPanel, null);
        add(tabbedPane);
        setContentPane(tabbedPane);

//        updatePanel();
//        createTools();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  sets the given tool as the activeTool
    public void setActiveTool(Tool tool) {
        if (activeTool != null) {
            activeTool.deactivate();
        }
        tool.activate();
        activeTool = tool;
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {

        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.EAST);
        add(toolArea, test);

        SaveTool saveTool = new SaveTool(this, simpleBorders);

        tools.add(saveTool);

        LoadTool loadTool = new LoadTool(this, simpleBorders);

        tools.add(loadTool);

//        setActiveTool(null);
    }

    // MODIFIES: this
    // EFFECTS:  declares and instantiates a Drawing (newDrawing), and adds it to drawings
    private void updatePanel() {
        JPanel jPanel = new LabelChanger();
        currentPanel = jPanel;
        add(ltest, test);
        test.add(new TroopPanel(elf));
        add(new TroopPanel(undead), test);
        validate();
    }
}
