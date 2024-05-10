package main.azul;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

/**
 * Class representing the bag of tiles.
 */
public class Bag implements Serializable {

    /**
    * Map representing contents of the bag.
    */
    private final Map<Tile, Stack<Tile>> bagMap;
    private final Box linkedBox;

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
    public Bag(Box linkedBox, int blacks, int whites, int reds, int yellows, int blues) {
      this.linkedBox = linkedBox;
      this.bagMap = new HashMap<>();
      for (Tile color : Tile.values()) {
        bagMap.put(color, new Stack<>());
      }
      addToBag(Tile.BLACK,blacks);
      addToBag(Tile.WHITE,whites);
      addToBag(Tile.RED,reds);
      addToBag(Tile.YELLOW,yellows);
      addToBag(Tile.BLUE,blues);
    }

    public Map<Tile, Stack<Tile>> getBagMap() {
      return this.bagMap;
    }

  public void printBagContents() {
    String BLUE = "\u001B[34m";
    System.out.println(BLUE + "Bag contents:");
    System.out.println("Size: " + this.size());
    System.out.println("Black: " + this.bagMap.get(Tile.BLACK).size() + " White: " + this.bagMap.get(Tile.WHITE).size() +
            " Red: " + this.bagMap.get(Tile.RED).size() + " Yellow: " + this.bagMap.get(Tile.YELLOW).size() +
            " Blue: " + this.bagMap.get(Tile.BLUE).size());
    System.out.println();
  }

    /**
   * Adds a certain amount of tiles of a specified color to the bag.
   * @param color - color of the tiles to be added
   * @param amount - amount of the tiles to be added
   */
    public void addToBag(Tile color, int amount) {
        for(int i = 0; i < amount; i++) {
            this.bagMap.get(color).push(color);
        }
    }

    /**
     * Refills the bag based on the contents of the box.
     */
    public void refillTheBagFromTheBox (){
      int[] tilesToAdd = this.linkedBox.retrieveContents();
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
            if (bagMap.get(Tile.BLACK).isEmpty()) {
              continue;
            }
            return bagMap.get(Tile.BLACK).pop();
          case 2:
            if (bagMap.get(Tile.WHITE).isEmpty()) {
              continue;
            }
            return bagMap.get(Tile.WHITE).pop();
          case 3:
            if (bagMap.get(Tile.RED).isEmpty()) {
              continue;
            }
            return bagMap.get(Tile.RED).pop();
          case 4:
            if (bagMap.get(Tile.YELLOW).isEmpty()) {
              continue;
            }
            return bagMap.get(Tile.YELLOW).pop();
          case 5:
            if (bagMap.get(Tile.BLUE).isEmpty()) {
              continue;
            }
            return bagMap.get(Tile.BLUE).pop();
        }
      }
    }

    /**
     * Returns the current number of tiles in the bag.
     *
     * @return The current number of tiles in the bag.
     */
    public int size(){
      int size = 0;
      for (Tile color : Tile.values()) {
        size += bagMap.get(color).size();
      }
      return size;
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if the bag is empty, false otherwise.
     */
    public boolean isEmpty(){ return this.size() == 0; }
}
