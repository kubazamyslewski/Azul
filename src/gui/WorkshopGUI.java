package gui;

import azul.Tile;


import javax.swing.*;
import java.awt.*;

public class WorkshopGUI extends JLabel {
    Tile[] tiles = new Tile[4];

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

