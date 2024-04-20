package azul;

import java.util.Stack;

/**
 * Class representing the box of tiles.
 */
public class Box {
    private Stack<Tile> blackTiles = new Stack<>();
    private Stack<Tile> whiteTiles = new Stack<>();
    private Stack<Tile> redTiles = new Stack<>();
    private Stack<Tile> yellowTiles = new Stack<>();
    private Stack<Tile> blueTiles = new Stack<>();
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
     * Returns an array of numbers representing amounts of tiles of a specified color in the box.
     * Then clears the contents of the box.
     */
    public int[] get() {
      int[] tilesFromTheBox = new int[5];
      int counter = 0;
      while (!blackTiles.isEmpty()) {
        blackTiles.pop();
        counter++;
      }
      tilesFromTheBox[0] = counter;
      counter = 0;
      while (!whiteTiles.isEmpty()) {
        whiteTiles.pop();
        counter++;
      }
      tilesFromTheBox[1] = counter;
      counter = 0;
      while (!redTiles.isEmpty()) {
        redTiles.pop();
        counter++;
      }
      tilesFromTheBox[2] = 0;
      counter = 0;
      while (!yellowTiles.isEmpty()) {
        yellowTiles.pop();
        counter++;
      }
      tilesFromTheBox[3] = counter;
      counter = 0;
      while (!blueTiles.isEmpty()) {
        blueTiles.pop();
        counter++;
      }
      tilesFromTheBox[4] = counter;
      return tilesFromTheBox;
    }
}
