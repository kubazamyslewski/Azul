package azul;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the box of tiles.
 */
public class Box {
    private List<Tile> blackTiles = new ArrayList<>();
    private List<Tile> whiteTiles = new ArrayList<>();
    private List<Tile> redTiles = new ArrayList<>();
    private List<Tile> yellowTiles = new ArrayList<>();
    private List<Tile> blueTiles = new ArrayList<>();
    /**
     * Default constructor for the box.
     */
    public Box() {
    }

    /**
     * Adds a tile to the contents of the box.
     *
     * @param colour color of the tile to be added.
     */
    public void add(Tile colour) {
    }

    /**
     * Returns a list of two-element arrays, where the first element is the color of the tile,
     * and the second element is the number of tiles of that color in the box. Then clears the contents of the box.
     */
    public List<Object[]> get() {
        List<Object[]> result = new ArrayList<>();
        return result;
    }
}
