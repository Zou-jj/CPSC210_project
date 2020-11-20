package ui.panels;

import model.Game;
import model.Troop;
import model.Warrior;
import sound.MidiSynth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TroopPanel extends JPanel implements ActionListener, FocusListener {
    public Troop troop;

    private MidiSynth midiSynth;

    private JTextField nameField;
    private JTextField attackField;
    private JTextField defenseField;
    protected JTextArea textArea;
    static final int GAP = 10;

    public TroopPanel(Troop troop) {
        super();
        this.troop = troop;
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setLayout(new GridLayout(0, 1));

        nameField = new JTextField(50);

        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(createEntryFields());
        add(createDisplay());
        add(createButtons());

        initializeSound();

    }

    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());

        String[] labelStrings = {
                "Name: ",
                "Attack: ",
                "Defense: "
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
        nameField = new JTextField();
        nameField.setColumns(20);
        fields[fieldNum++] = nameField;

        attackField = new JTextField();
        attackField.setColumns(20);
        fields[fieldNum++] = attackField;

        defenseField = new JTextField();
        defenseField.setColumns(20);
        fields[fieldNum++] = defenseField;

        pairLabelField(panel, labelStrings, labels, fields);

        return panel;
    }

    private void pairLabelField(JPanel panel, String[] labelStrings, JLabel[] labels, JComponent[] fields) {
        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                    JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            JTextField tf = null;
            if (fields[i] instanceof JSpinner) {
                tf = getTextField((JSpinner) fields[i]);
            } else {
                tf = (JTextField) fields[i];
            }
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }
        SpringUtilities.makeCompactGrid(panel,
                labelStrings.length, 2,
                GAP, GAP, //init x,y
                GAP, GAP / 2);//xpad, ypad
    }

    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                    + spinner.getEditor().getClass()
                    + " isn't a descendant of DefaultEditor");
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: create a display panel
    protected JComponent createDisplay() {
        textArea = new JTextArea(10, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        updateDisplays();

        return scrollPane;
    }

    // MODIFIES: this
    // EFFECTS: update the display panel
    protected void updateDisplays() {
        String text = nameField.getText();
        textArea.append(text + "\n");
        nameField.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    protected void clearDisplay() {
        textArea.setText("");
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

    //Workaround for formatted text field focus side effects.
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


        JButton button = new JButton("Add");
        button.addActionListener(this);
        button.setActionCommand("add");
        panel.add(button);

        button = new JButton("Clear display");
        button.addActionListener(this);
        button.setActionCommand("clear");
        panel.add(button);

        button = new JButton("List troop");
        button.addActionListener(this);
        button.setActionCommand("list");
        panel.add(button);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                GAP - 5, GAP - 5));
        return panel;
    }

    // MODIFIES: this, troop
    // EFFECTS: add a warrior to the given troop
    private void addWarrior(Troop troop) {
        Warrior newWarrior;
        String name;
        boolean contain = false;
        int index = 0;

        name = nameField.getText();

        for (; index < troop.getTroopSize(); index++) {
            if (troop.getWarriorByIndex(index).getName().equals(name)) {
                contain = true;
                break;
            }
        }

        if (!contain) {
            newWarrior = new Warrior(name);
            troop.addWarrior(newWarrior);
            print("Added a " + newWarrior.getName() + " to " + troop.getRace());
            editWarriorAttack(newWarrior);
            editWarriorDefense(newWarrior);
        } else {
            editWarriorAttack(troop.getWarriorByIndex(index));
            editWarriorDefense(troop.getWarriorByIndex(index));
        }
    }

    // MODIFIES: this, warrior
    // EFFECTS: prompts user to edit attack of the given warrior
    private void editWarriorAttack(Warrior warrior) {
        int attack;

        try {
            String atk = attackField.getText();
            attack = Integer.parseInt(atk);
            if (attack > 0) {
                warrior.setAttack(attack);
                print(warrior.getName() + "'s attack changed to " + warrior.getAttack());
            } else {
                print("Enter a positive attack...");
            }
        } catch (NumberFormatException e) {
            print("Invalid attack...");
        }
    }

    // MODIFIES: this, warrior
    // EFFECTS: prompts user to edit defense of the given warrior
    private void editWarriorDefense(Warrior warrior) {
        int defense;

        try {
            String def = defenseField.getText();
            defense = Integer.parseInt(def);
            if (defense > 0) {
                warrior.setDefense(defense);
                print(warrior.getName() + "'s defense changed to " + warrior.getDefense());
            } else {
                print("Enter a positive defense...");
            }
        } catch (NumberFormatException e) {
            print("Invalid defense...");
        }
    }

    // MODIFIES: this
    // EFFECTS: print the message on the display panel
    private void print(String str) {
        textArea.append("\n" + str + "\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    // EFFECTS: list all warriors in the given troop and process user input
    private void listTroop() {
        print("Warriors available:\n");
        for (int index = 0; index < troop.getTroopSize(); index++) {
            print("\t Name: " + troop.getWarriorByIndex(index).getName());
            print("\t Attack: " + troop.getWarriorByIndex(index).getAttack());
            print("\t Defense: " + troop.getWarriorByIndex(index).getDefense() + "\n");
        }
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
        if (e.getActionCommand().equals("add")) {
            addWarrior(troop);
            midiSynth.play(10,50,100);
        } else if (e.getActionCommand().equals("clear")) {
            clearDisplay();
            midiSynth.play(10,60,100);
        } else if (e.getActionCommand().equals("list")) {
            listTroop();
            midiSynth.play(10,70,100);
        }
    }

}

/*
 *Title:SimpleDrawingPlayer-Complete
 *Author:Norm Hutchinson
 *Date:Oct 19, 2019
 *Availability:https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
 */