package main.azul;

/**
 * Part of the board for tiles that give point penalties
 */
public class Floor {

    private final Board parentBoard;
    private final Tile[] tilesOnTheFloor;
    private int amountOfTiles;

    /**
     * Constructor for the Floor class.
     */
    public Floor(Board parentBoard) {
        this.parentBoard = parentBoard;
        this.amountOfTiles = 0;
        this.tilesOnTheFloor = new Tile[7];
    }

    /**
     * Displays the floor
     */
    public void displayFloor() {

        String RED = "\u001B[31m";

        System.out.println(RED + "Floor:");
        for(Tile t : this.tilesOnTheFloor) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    /**
     * Returns the number of tiles placed on the floor.
     * @return - Number of tiles on the floor.
     */
    public int getAmountOfTiles() {
        return this.amountOfTiles;
    }

    /**
     * Returns the floor state as an array of tiles.
     * @return - Array of tiles representing the floor state.
     */
    public Tile[] getTilesOnTheFloor() { return this.tilesOnTheFloor; }

    /**
     * Adds tiles to the floor.
     * @param tile - A tile to be added to the floor.
     */
    public void addTileToFloor(Tile tile) {
        if (amountOfTiles < tilesOnTheFloor.length) this.tilesOnTheFloor[this.amountOfTiles++] = tile;
        else this.parentBoard.getLinkedBox().add(tile);
    }

    /**
     * Clears tiles from the floor and puts them in the box.
     */
    public void clearFloor(){
        for(int i = 6; i >= 0; i--) {
            if(this.tilesOnTheFloor[i] != null) this.parentBoard.getLinkedBox().add(this.tilesOnTheFloor[i]);
            this.tilesOnTheFloor[i] = null;
            this.amountOfTiles--;
        }
    }
    public boolean containsFirstTile() {
        for (int i = 0; i < amountOfTiles; i++) {
            if (tilesOnTheFloor[i] == Tile.FIRST) {
                return true;
            }
        }
        return false;
    }
}
