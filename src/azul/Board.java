package azul;

/**
 * Player's board representation.
 */

public class Board {

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
        return null;
    }

    /**
     * Gets the temporary mosaic.
     * @return - The temporary mosaic.
     */
    public Mosaic getTemporaryMosaic() {
        return null;
    }

    /**
     * Gets the mosaic.
     * @return - The mosaic.
     */
    public Mosaic getMosaic() {
        return null;
    }

    /**
     * Gets the value of 'start' which represents the starting player.
     * @return - Boolean value of 'start'.
     */
    public boolean isStartingPlayer() {
        return false;
    }

    /**
     * Sets the value of 'start'.
     * @param start - Boolean value to set 'start'.
     */
    public void setStartingPlayer(boolean start) {

    }
    /**
     * Gets available colours.
     * @return - Array of tiles representing the available colours.
     */
    public Tile[] getAvailableColours() {
        return null;
    }

    /**
     * Sets the tile drawing pool.
     * @param drawingPool - The tile drawing pool to be set.
     */
    public void setDrawingPool(TileDrawingPool drawingPool){
    }

    /**
     * Returns the color of tiles arranged in a specific row on the wall.
     * @param row - Row number from 0 to 4
     * @return - Tile representing the color of the row.
     */
    public Tile getColorOfTheRow(int row){
        return null;
    }

    /**
     * Converts the mosaic into a 5x5 boolean matrix to check where are the placed tiles.
     * @return - 5x5 boolean matrix.
     */
    public boolean[][] exportMosaic() {
        return null;
    }

    /**
     * Generates a 5x5 matrix representing the wall state (empty spot = null).
     * @return - 5x5 matrix representing the wall state.
     */
    public Tile[][] exportWall() {
        return null;
    }

    /**
     * Adds tiles to a row on the wall.
     * @param row   - Row number.
     * @param tiles - Array of tiles to be added.
     */
    public void addTilesToWall(int row, Tile[] tiles) {

    }

    /**
     * Clears specific row on the wall.
     * @param row - Row number to be cleared.
     */
    private void clearRowOnWall(int row) {

    }

    /**
     * Pushes tiles from the wall to the mosaic.
     */
    void pushWallToMosaic() {
    }

    /**
     * Initializes the wall.
     */
    private void initializeWall() {
    }

    /**
     * Calculates the number of free spots in a specific row on the wall.
     * @param rowNumber - Row number.
     * @return - Number of free spots in the row.
     */
    public int freeSpotsInRow(int rowNumber) {
        return 0;
    }

    /**
     * Checks if a row on the mosaic is completed.
     * @return - Boolean value indicating if a row on the mosaic is completed.
     */
    public boolean isRowCompleted(){
        return false;
    }
}
