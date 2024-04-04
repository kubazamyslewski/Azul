package gui;

import javax.swing.*;
import java.awt.*;


public class InputWorkshopGUI extends Container {
        WorkshopButton[] buttons = new WorkshopButton[4];
        WorkshopGUI workshopGUI;

        public InputWorkshopGUI(WorkshopGUI workshopGUI) {
            this.workshopGUI = workshopGUI;

            for (int i = 0; i < 4; i++) {
                buttons[i] = new WorkshopButton(i,this);
            }
        }

        void buttonPressed(int buttonNumber){
            // Method for handling button press
        }

        class WorkshopButton extends JButton {
            gui.InputWorkshopGUI inputWorkshopGUI;
            int numberOfButton;

            public WorkshopButton(int numberOfButton, gui.InputWorkshopGUI inputWorkshopGUI) {
                this.inputWorkshopGUI = inputWorkshopGUI;
                this.numberOfButton = numberOfButton;
            }

            public void displayAsSelected(boolean selected){
                // Method for displaying selection state of the button
            }
        }
}
