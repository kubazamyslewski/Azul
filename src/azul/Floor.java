package azul;

public class Floor {

    /**
     * Constructor for the Floor class.
     */
    public Floor() {
    }

    /**
     * Returns the number of tiles placed on the floor.
     * @return - Number of tiles on the floor.
     */
    public int getNumberOfTilesOnTheFloor() {
        return 0;
    }

    /**
     * Returns the floor state as an array of tiles.
     * @return - Array of tiles representing the floor state.
     */
    public Tile[] exportFloor() {
        Tile[] tiles = new Tile[0];
        return tiles;
    }

    /**
     * Adds tiles to the floor.
     * @param tiles - Array of tiles to be added to the floor.
     */
    public void addTilesToFloor(Tile[] tiles) {
    }


    /**
     * Adds the "start" tile to the floor.
     */
    public void addFirstPlayerTileToFloor(){
    }


    /**
     * Clears tiles from the floor and puts them in the box.
     */
    public void clearFloor(){
    }


    /**
     * Calculates the number of free spots on the floor.
     * @return - Number of free spots on the floor.
     */
    private int freeSpotsOnTheFloor(){
        return 0;
    }
}
