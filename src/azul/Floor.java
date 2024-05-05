package azul;

/**
 * Part of the board for tiles that give point penalties
 */
public class Floor {

    Board parentBoard;
    private Tile[] tiles;
    int amountOfTiles;
    /**
     * Constructor for the Floor class.
     */
    public Floor(Board parentBoard) {
        this.parentBoard = parentBoard;
        this.amountOfTiles = 0;
        this.tiles = new Tile[7];
    }


    /**
     * Returns the number of tiles placed on the floor.
     * @return - Number of tiles on the floor.
     */
    public int getNumberOfTilesOnTheFloor() {
        return this.amountOfTiles;
    }

    /**
     * Returns the floor state as an array of tiles.
     * @return - Array of tiles representing the floor state.
     */
    public Tile[] exportFloor() {
        return tiles;
    }

    /**
     * Adds tiles to the floor.
     * @param tile - A tile to be added to the floor.
     */
    public void addTileToFloor(Tile tile) {
        if (amountOfTiles < tiles.length) this.tiles[this.amountOfTiles++] = tile;
        else this.parentBoard.linkedBox.add(tile);
    }

    /**
     * Clears tiles from the floor and puts them in the box.
     */
    public void clearFloor(Box box){
        for(int i = 6; i >= 0; i--) {
            box.add(this.tiles[i]);
            this.tiles[i] = null;
        }
    }
}
