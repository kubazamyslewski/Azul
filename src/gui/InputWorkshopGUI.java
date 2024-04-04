package gui;

import javax.swing.*;
import java.awt.*;

public class InputWorkshopGUI extends Container {
    WorkshopButton[] buttons = new WorkshopButton[4];
    WorkshopGUI workshopGUI;

    /**
     * Constructor for the InputWorkshopGUI class.
     *
     * @param workshopGUI the WorkshopGUI object associated with this input GUI
     */
    public InputWorkshopGUI(WorkshopGUI workshopGUI) {
        this.workshopGUI = workshopGUI;

        for (int i = 0; i < 4; i++) {
            buttons[i] = new WorkshopButton(i, this);
        }
    }

    /**
     * Method called when a button is pressed.
     *
     * @param buttonNumber the number of the button pressed
     */
    void buttonPressed(int buttonNumber) {
    }

    class WorkshopButton extends JButton {
        gui.InputWorkshopGUI inputWorkshopGUI;
        int numberOfButton;

        /**
         * Constructor for the WorkshopButton class.
         *
         * @param numberOfButton    the number associated with this button
         * @param inputWorkshopGUI  the InputWorkshopGUI object associated with this button
         */
        public WorkshopButton(int numberOfButton, gui.InputWorkshopGUI inputWorkshopGUI) {
            this.inputWorkshopGUI = inputWorkshopGUI;
            this.numberOfButton = numberOfButton;
        }

        /**
         * Method to display the selection state of the button.
         *
         * @param selected  true if the button should be displayed as selected, false otherwise
         */
        public void displayAsSelected(boolean selected) {
        }
    }
}
