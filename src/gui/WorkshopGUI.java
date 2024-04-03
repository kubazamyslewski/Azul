package gui;

import azul.Tile;


import javax.swing.*;
import java.awt.*;

public class WorkshopGUI extends JLabel {
    Tile[] tiles = new Tile[4];
    private InputWorkshopGUI inputWorkshopGUI = new InputWorkshopGUI(this);
    int workshopNumber;

    public WorkshopGUI(int workshopNumber) {

    }

    void buttonPressed(int buttonNumber){
        // Method for handling button press
    }

    public void updateWorkshop(Tile[] newTiles){
        // Method for updating the workshop state
    }

    Tile[] getTiles(){
        // Method for retrieving the tiles
        return null;
    }

    public void displaySelection(Tile color) {
        // Method for highlighting tiles of a given color
    }

    void updateDisplayWorkshop(){
        // Method for updating the display of the workshop
    }
}





class InputWorkshopGUI extends Container {
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
        InputWorkshopGUI inputWorkshopGUI;
        int numberOfButton;

        public WorkshopButton(int numberOfButton, InputWorkshopGUI inputWorkshopGUI) {
            this.inputWorkshopGUI = inputWorkshopGUI;
            this.numberOfButton = numberOfButton;
        }

        public void displayAsSelected(boolean selected){
            // Method for displaying selection state of the button
        }
    }
}
