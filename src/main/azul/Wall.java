package main.azul;

import main.Exceptions.WrongTileColourException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class representing the area where players put tiles before putting them on the mosaic
 */
public class Wall implements Serializable {

    private final Board parentBoard;

    /**
    * Map representing contents rows of the wall
    */
    private final Map<Integer, Tile[]> wallMap;

    private final Stack<Tile> tempStorage = new Stack<>();

    public Wall(Board parentBoard){

      this.parentBoard = parentBoard;
      this.wallMap = new HashMap<>();
      for (int i = 0; i < 5; i++) {
        this.wallMap.put(i, new Tile[i+1]);
      }

    }

    /**
    * Prints the wall to the console
    */
    public void printWall() {

      String RED = "\u001B[31m";

      System.out.println(RED + "Wall:");
      for (int i = 0; i < 5; i++) {
        for (Tile t : this.wallMap.get(i)) {
            if(t==null) {
                System.out.print("_ ");
            }
            else {
                System.out.print(t + " ");
            }
        }
        System.out.println();
      }
      System.out.println();
    }

    public int countTilesInRow(int row) {
      int counter = 0;
      for (Tile t : this.wallMap.get(row-1)) {
        if (t != null) counter++;
      }
      return counter;
    }

    public Stack<Tile> getTempStorage() {
      return this.tempStorage;
    }

    /**
    * Adds tile to tempStorage in order to later transfer them to the wall
    * @param tile tile to be added to tempStorage
    */
    public void addToTempStorage(Tile tile){ tempStorage.push(tile); }

    /**
     * Returns the color of tiles arranged in a specific row on the wall.
     * @param row - Row number from 1 to 5
     * @return - Tile representing the color of the row.
     * @throws IllegalArgumentException - if the specified row of the wall is invalid
     */
    public Tile getColorOfTheRow(int row) throws IllegalArgumentException{

      if (row < 1 || row > 5) throw new IllegalArgumentException("No such row on the wall!");

      return this.wallMap.get(row-1)[0];
    }

    public Map<Integer, Tile[]> getWallMap() {
      return this.wallMap;
    }

    /**
     * Adds tiles to a row on the wall or to the floor if the row becomes full.
     * Checks whether it is possible to place certain colour of tiles on a specified row
     * and throws an exception otherwise.
     * @param row   - Row number.
     */
    public void addTilesToWall(int row) throws WrongTileColourException{

      if (row < 0 || row > 4) throw new IllegalArgumentException("No such row on the wall!");

      Tile colour;
      Tile[] tilesToAdd = new Tile[tempStorage.size()];
      int sizeOfTempStorage = tempStorage.size();
      for (int i = 0; i < sizeOfTempStorage; i++) {
        tilesToAdd[i] = tempStorage.pop();
      }

      Tile[] modifiedRow = this.wallMap.get(row);

      colour = modifiedRow[0];
      if (((colour != null) && (!tilesToAdd[0].equals(colour))) || (parentBoard.getMosaic().checkRowForColor(tilesToAdd[0], row)))
        throw new WrongTileColourException("You cannot put this color in this row!");

      int tilesToAddIndex = 0;
      // Add to the row on the wall
      for (int i = 0; i < modifiedRow.length; i++) {
        if((modifiedRow[i] == null) && (tilesToAddIndex < tilesToAdd.length)) {
          modifiedRow[i] = tilesToAdd[tilesToAddIndex++];
        }
      }
      // Add the rest to the floor
      while (tilesToAddIndex < tilesToAdd.length) {
        this.parentBoard.getFloor().addTileToFloor(tilesToAdd[tilesToAddIndex++]);
      }
    }

  public boolean checkIfRowIsSafeForColor(int row, Tile color) {
    for (int i = 0; i < this.wallMap.get(row).length; i++) {
      if (this.wallMap.get(row)[i] != null && !this.wallMap.get(row)[i].equals(color)) return false;
    }
    return true;
  }

    /**
     * Pushes tiles from the wall to the mosaic.
     */
    public void checkWallAndPushToMosaic() throws WrongTileColourException {
      for (int i = 1; i <= 5; i++) {
        if (checkRow(i)) pushRowToMosaic(i);
      }
    }

    private boolean checkRow(int row) {

      for (Tile t : this.wallMap.get(row-1)) {
        if (t == null) return false;
      }
      return true;

    }

    private void pushRowToMosaic(int row) throws WrongTileColourException, IllegalArgumentException{
      Tile color = getColorOfTheRow(row);
      parentBoard.getMosaic().setTile(row, color);
      clearRowOnWall(row);
    }

    private void clearRowOnWall(int row) {

      for (Tile t : wallMap.get(row-1)) {
        parentBoard.getLinkedBox().add(t);
      }
      Arrays.fill(wallMap.get(row-1), null);

    }

}
