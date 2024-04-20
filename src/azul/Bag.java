package azul;

import java.util.Random;
import java.util.Stack;

/**
 * Class representing the bag of tiles.
 */
public class Bag {
    private final Stack<Tile> blackTiles = new Stack<>();
    private final Stack<Tile> whiteTiles = new Stack<>();
    private final Stack<Tile> redTiles = new Stack<>();
    private final Stack<Tile> yellowTiles = new Stack<>();
    private final Stack<Tile> blueTiles = new Stack<>();

    /**
     * Constructor for the bag.
     * Creates a bag with a specified number of tiles of different colors.
     *
     * @param blacks   Number of black tiles.
     * @param whites   Number of white tiles.
     * @param reds     Number of red tiles.
     * @param yellows  Number of yellow tiles.
     * @param blues    Number of blue tiles.
     */
    public Bag(int blacks, int whites, int reds, int yellows, int blues) {
      addToBag(Tile.BLACK,blacks);
      addToBag(Tile.WHITE,whites);
      addToBag(Tile.RED,reds);
      addToBag(Tile.YELLOW,yellows);
      addToBag(Tile.BLUE,blues);
    }

    private void addToBag(Tile colour, int amount) {
      switch (colour) {
        case BLACK:
          for (int i = 0; i < amount; i++) {
            this.blackTiles.push(Tile.BLACK);
          }
          break;
        case WHITE:
          for (int i = 0; i < amount; i++) {
            this.whiteTiles.push(Tile.WHITE);
          }
          break;
        case RED:
          for (int i = 0; i < amount; i++) {
            this.redTiles.push(Tile.RED);
          }
          break;
        case YELLOW:
          for (int i = 0; i < amount; i++) {
            this.yellowTiles.push(Tile.YELLOW);
          }
          break;
        case BLUE:
          for (int i = 0; i < amount; i++) {
            this.blueTiles.push(Tile.BLUE);
          }
          break;
        default:
          throw new IllegalArgumentException("No such tile exists!");
      }
    }

    /**
     * Refills the bag based on the contents of the box.
     *
     * @param box The box with tiles.
     */
    public void addToBagFromTheBox (Box box){
      int[] tilesToAdd = box.get();
      addToBag(Tile.BLACK, tilesToAdd[0]);
      addToBag(Tile.WHITE, tilesToAdd[1]);
      addToBag(Tile.RED, tilesToAdd[2]);
      addToBag(Tile.YELLOW, tilesToAdd[3]);
      addToBag(Tile.BLUE, tilesToAdd[4]);
    }

    /**
     * Retrieves a tile from the bag (removes it from the top of the bag) and returns it.
     *
     * @return The tile retrieved from the bag. Null, if the bag is empty.
     */
    public Tile pop() {
      Random random = new Random();
      if (this.isEmpty()) {
        System.out.println("The bag is empty!");
        return null;
      }
      while (true) {
        int r = random.nextInt(5) + 1;
        switch (r) {
          case 1:
            if (blackTiles.isEmpty()) {
              continue;
            }
            return blackTiles.pop();
          case 2:
            if (whiteTiles.isEmpty()) {
              continue;
            }
            return whiteTiles.pop();
          case 3:
            if (redTiles.isEmpty()) {
              continue;
            }
            return redTiles.pop();
          case 4:
            if (yellowTiles.isEmpty()) {
              continue;
            }
            return yellowTiles.pop();
          case 5:
            if (blueTiles.isEmpty()) {
              continue;
            }
            return blueTiles.pop();
        }
      }
    }

    /**
     * Returns the current number of tiles in the bag.
     *
     * @return The current number of tiles in the bag.
     */
    public int size(){
      return blackTiles.size() + whiteTiles.size() + redTiles.size()
              + yellowTiles.size() + blueTiles.size();
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if the bag is empty, false otherwise.
     */
    public boolean isEmpty(){
      return this.size() == 0;
    }
}
