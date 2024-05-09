package main.gui;

import main.azul.Tile;
import main.azul.Workshop;

import javax.swing.*;

public class WorkshopGUI extends JLabel {

    private Workshop workshop;
    Tile[] tiles = new Tile[4];

    int workshopNumber;

    /**
     * Constructor for the WorkshopGUI class.
     *
     * @param workshopNumber the number associated with this workshop
     */
    public WorkshopGUI(int workshopNumber) {

    }

    /**
     * Method called when a button is pressed.
     *
     * @param buttonNumber the number of the button pressed
     */
    void buttonPressed(int buttonNumber){

    }

    /**
     * Method to update the workshop state.
     *
     * @param newTiles the new tiles to update the workshop with
     */
    public void updateWorkshop(Tile[] newTiles){

    }

    /**
     * Method to retrieve the tiles.
     *
     * @return the tiles in the workshop
     */
    Tile[] getTiles(){
        return null;
    }

    /**
     * Method to highlight tiles of a given color.
     *
     * @param color the color of tiles to highlight
     */
    public void displaySelection(Tile color) {

    }

    /**
     * Method to update the display of the workshop.
     */
    void updateDisplayWorkshop(){

    }
}
