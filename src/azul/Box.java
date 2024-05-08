package azul;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class representing the box of tiles.
 */
public class Box {

    /**
    * Map representing contents of the box.
    */
    private final Map<Tile, Stack<Tile>> boxMap;

    /**
     * Default constructor for the box.
     */
    public Box() {
      this.boxMap = new HashMap<>();
      for (Tile color : Tile.values()) {
        boxMap.put(color, new Stack<>());
      }
    }

    public void printBoxContents() {
      String BLUE = "\u001B[34m";
      System.out.println(BLUE + "Box contents:");
      System.out.println("Size: " + this.size());
      System.out.println("Black: " + this.boxMap.get(Tile.BLACK).size() + " White: " + this.boxMap.get(Tile.WHITE).size() +
              " Red: " + this.boxMap.get(Tile.RED).size() + " Yellow: " + this.boxMap.get(Tile.YELLOW).size() +
              " Blue: " + this.boxMap.get(Tile.BLUE).size());
      System.out.println();
    }

    /**
     * Adds a tile to the contents of the box.
     *
     * @param color color of the tile to be added.
     */
    public void add(Tile color) {
        this.boxMap.get(color).push(color);
    }

    /**
     * Returns an array of numbers representing amounts of tiles of a specified color in the box.
     * Then clears the contents of the box.
     * @return an array representing amounts of tiles of certain colors in the box:
     * [BLACK, WHITE, RED, YELLOW, BLUE]
     */
    public int[] retrieveContents() {

        int[] tilesFromTheBox = new int[5];

        int counter = 0;
        while (!this.boxMap.get(Tile.BLACK).isEmpty()) {
          this.boxMap.get(Tile.BLACK).pop();
          counter++;
        }
        tilesFromTheBox[0] = counter;

        counter = 0;
        while (!this.boxMap.get(Tile.WHITE).isEmpty()) {
          this.boxMap.get(Tile.WHITE).pop();
          counter++;
        }
        tilesFromTheBox[1] = counter;

        counter = 0;
        while (!this.boxMap.get(Tile.RED).isEmpty()) {
          this.boxMap.get(Tile.RED).pop();
          counter++;
        }
        tilesFromTheBox[2] = counter;

        counter = 0;
        while (!this.boxMap.get(Tile.YELLOW).isEmpty()) {
          this.boxMap.get(Tile.YELLOW).pop();
          counter++;
        }
        tilesFromTheBox[3] = counter;

        counter = 0;
        while (!this.boxMap.get(Tile.BLUE).isEmpty()) {
          this.boxMap.get(Tile.BLUE).pop();
          counter++;
        }
        tilesFromTheBox[4] = counter;

        return tilesFromTheBox;
    }

    public int size() {
      return this.boxMap.get(Tile.BLACK).size() + this.boxMap.get(Tile.WHITE).size() +
              this.boxMap.get(Tile.RED).size() + this.boxMap.get(Tile.YELLOW).size() +
              this.boxMap.get(Tile.BLUE).size();
    }
}
