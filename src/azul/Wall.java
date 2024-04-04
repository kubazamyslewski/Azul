package azul;

/**
 * Class representing the area where players put tiles before putting them on mosaic
 */
public class Wall {
    private Tile[][] wall;
    private Tile tile;
    public Wall(){

    }

    /**
     * Returns the color of tiles arranged in a specific row on the wall.
     * @param row - Row number from 0 to 4
     * @return - Tile representing the color of the row.
     */
    public Tile getColorOfTheRow(int row){
        return tile;
    }

    /**
     * Generates a 5x5 matrix representing the wall state (empty spot = null).
     * @return - 5x5 matrix representing the wall state.
     */
    public Tile[][] exportWall() {
        return wall;
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
}
