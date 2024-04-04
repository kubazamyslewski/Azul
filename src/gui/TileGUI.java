package gui;
import azul.Tile;

import javax.swing.*;

public class TileGUI extends JLabel {
    private boolean visible;
    private Tile tile;

    /**
     * Constructor for the TileGUI class.
     * @param visible Indicates whether the tile is initially visible or not.
     */
    public TileGUI(boolean visible) {
        this.visible = visible;
    }

    /**
     * Method to display or hide the tile.
     * @param visible True to display the tile, false to hide it.
     */
    public void displayThis(boolean visible) {
        this.visible = visible;
        setVisible(visible);
    }

    /**
     * Method to change the image of the tile based on the Tile object.
     * @param tile The Tile object representing the new image for the tile.
     */
    public void changeImage(Tile tile) {
        // Change the image of the tile based on the Tile object
        // Implementation code goes here
    }
}
