package ui.tools;

import model.Game;
import ui.WarSim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveTool extends Tool {
    WarSim warSim;

    public SaveTool(WarSim warSim, JComponent parent) {
        super(warSim, parent);
        this.warSim = warSim;
    }

    // MODIFIES: this
    // EFFECTS:  constructs a delete button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new SaveToolClickHandler());
        button.addActionListener(warSim.ltest);
        button.setActionCommand("save");
    }

    private class SaveToolClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the delete tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            Game.saveWorld();
        }
    }
}
