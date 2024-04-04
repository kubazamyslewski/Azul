package azul;

/**
 * Player's board representation.
 */

public class Board {
    private Floor floor = new Floor();
    private Wall wall = new Wall();
    private Mosaic mosaic = new Mosaic();
    private Mosaic currentMosaic = new Mosaic();
    private Tile[] availableColours=  new Tile[5];
    private String[] pushedToMosaic;

    /**
     * Constructor for the Board class.
     */
    public Board() {
    }

    /**
     * Gets the tiles pushed to the mosaic.
     * @return - Array of strings representing the tiles pushed to the mosaic.
     */
    public String[] getTilesPushedToMosaic() {
        return pushedToMosaic;
    }

    /**
     * Gets the current mosaic.
     * @return - The current mosaic.
     */
    public Mosaic getCurrentMosaic() {
        return currentMosaic;
    }

    /**
     * Gets the full mosaic.
     * @return - The mosaic.
     */
    public Mosaic getFullMosaic() {
        return mosaic;
    }
    
    /**
     * Gets available colours.
     * @return - Array of tiles representing the available colours.
     */
    public Tile[] getAvailableColours() {
        return availableColours;
    }

}
