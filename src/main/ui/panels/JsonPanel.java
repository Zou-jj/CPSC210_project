package ui.panels;

import model.Game;
import sound.MidiSynth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

// represent a editing panel of some troop
public class JsonPanel extends JPanel implements ActionListener, FocusListener {

    private MidiSynth midiSynth;

    protected JTextArea textArea;
    static final int GAP = 10;

    public JsonPanel() {
        super();
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setLayout(new GridLayout(0, 1));
        add(createDisplay());
        add(createButtons());
        initializeSound();
    }

    // MODIFIES: this
    // EFFECTS: create a display panel
    protected JComponent createDisplay() {
        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        return scrollPane;
    }

    /**
     * Called when one of the fields gets the focus so that
     * we can select the focused field.
     */
    public void focusGained(FocusEvent e) {
        Component c = e.getComponent();
        if (c instanceof JFormattedTextField) {
            selectItLater(c);
        } else if (c instanceof JTextField) {
            ((JTextField) c).selectAll();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    // EFFECTS: workaround for formatted text field focus side effects.
    protected void selectItLater(Component c) {
        if (c instanceof JFormattedTextField) {
            final JFormattedTextField ftf = (JFormattedTextField) c;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ftf.selectAll();
                }
            });
        }
    }

    // MODIFIES: this
    // EFFECTS: create buttons on the panel
    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));


        JButton button = new JButton("Save");
        button.addActionListener(this);
        button.setActionCommand("save");
        panel.add(button);

        button = new JButton("load");
        button.addActionListener(this);
        button.setActionCommand("load");
        panel.add(button);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                GAP - 5, GAP - 5));
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: print the message on the display panel
    private void print(String str) {
        textArea.append("\n" + str + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // MODIFIES: this
    // EFFECTS:  initializes this DrawingEditor's midisynth field, then calls open() on it
    private void initializeSound() {
        midiSynth = new MidiSynth();
        midiSynth.open();
    }

    // MODIFIES: this
    // EFFECTS: perform action according to the button being clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            Game.saveWorld();
            print("Saved game to " + Game.JSON_STORE);
            midiSynth.play(10,40,100);
        } else if (e.getActionCommand().equals("load")) {
            Game.loadWorld();
            print("Loaded game from " + Game.JSON_STORE);
            midiSynth.play(10,30,100);
        }
    }
}

/*
 *Title:SimpleDrawingPlayer-Complete
 *Author:Norm Hutchinson
 *Date:Oct 19, 2019
 *Availability:https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
 */