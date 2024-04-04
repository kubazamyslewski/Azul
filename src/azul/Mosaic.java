package azul;

/**
 * Mosaic representation.
 */
public class Mosaic {

    public Mosaic() {
    }

    /**
     * Converts the mosaic to a boolean matrix of size 5x5 to check where tiles are placed.
     * @return boolean[5][5]
     */
    public boolean[][] exportMosaic(){
        return new boolean[5][5];
    }

    /**
     * Initializes the mosaic squares.
     */
    private Tile[][] createMosaic() {
        return new Tile[5][5];
    }

    /**
     * Gets the placed tile on a specific mosaic square.
     * @param row    - Row index.
     * @param column - Column index.
     * @return - The placed tile.
     */
    public Tile getPlacedTile(int row, int column) {
        Tile tile = null;
        return tile;
    }

    /**
     * Sets the tile on a specific mosaic square.
     * @param row    - Row index.
     * @param column - Column index.
     * @param tile   - The tile to be placed.
     */
    public void setTile(int row, int column, Tile tile) {
    }

    /**
     * Checks if a row on the mosaic is completed.
     * @return - Boolean value indicating if a row on the mosaic is completed.
     */
    public boolean isRowCompleted(){
        return false;
    }
}

