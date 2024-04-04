package azul;

/**
 * Player's board representation.
 */

public class Board {
    private Floor floorr = new Floor();
    private Wall wall = new Wall();
    private Mosaic fullMosaic = new Mosaic();
    private Mosaic currentMosaic = new Mosaic();
    private Tile[][] mosaic;
    private Tile[] pushedToMosaic;

    /**
     * Constructor for the Board class.
     */
    public Board() {
    }

    public Floor getFloor() {
        return floorr;
    }

    public Wall getWall() {
        return wall;
    }

    /**
     * Gets the tiles pushed to the mosaic.
     * @return - Array of tiles pushed to the mosaic.
     */
    public Tile[] getTilesPushedToMosaic() {
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
     * Initializes the mosaic squares.
     */
    private Tile[][] createMosaic() {
        return mosaic;
    }
    /**
     * Gets the full mosaic.
     * @return - The mosaic.
     */
    public Mosaic getFullMosaic() {
        return fullMosaic;
    }

}
