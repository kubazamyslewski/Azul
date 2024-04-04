package azul;


import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the bag of tiles.
 */
public class Bag {
    private List<Tile> blackTiles = new ArrayList<>();
    private List<Tile> whiteTiles = new ArrayList<>();
    private List<Tile> redTiles = new ArrayList<>();
    private List<Tile> yellowTiles = new ArrayList<>();
    private List<Tile> blueTiles = new ArrayList<>();

    private Tile retrievedTile;
    /**
     * Constructor for the bag.
     * Creates a bag with a specified number of tiles of different colors and shuffles them.
     *
     * @param blacks   Number of black tiles.
     * @param whites   Number of white tiles.
     * @param reds     Number of red tiles.
     * @param yellows  Number of yellow tiles.
     * @param blues    Number of blue tiles.
     */
    public Bag(int blacks, int whites, int reds, int yellows, int blues) {
    }

    /**
     * Refills the bag based on the contents of the box.
     *
     * @param box The box with tiles.
     */
    public void addToBag (Box box){
    }

    /**
     * Retrieves a tile from the bag (removes it from the top of the bag) and returns it.
     *
     * @return The tile retrieved from the bag.
     */

    public Tile pop() {

        return retrievedTile;
    }

    /**
     * Returns the current number of tiles in the bag.
     *
     * @return The current number of tiles in the bag.
     */
    public int size(){

        return 0;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if the bag is empty, false otherwise.
     */
    public boolean isEmpty(){

        return false;
    }
}
