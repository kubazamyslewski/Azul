package azul;

import Exceptions.WrongTileColourException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class representing the area where players put tiles before putting them on the mosaic
 */
public class Wall {

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
      System.out.println("Wall:");
      System.out.println("Row 1 (1 fields): Color[" + this.getColorOfTheRow(1) + "] Filled: " + this.countTilesInRow(1));
      System.out.println("Row 2 (2 fields): Color[" + this.getColorOfTheRow(2) + "] Filled: " + this.countTilesInRow(2));
      System.out.println("Row 3 (3 fields): Color[" + this.getColorOfTheRow(3) + "] Filled: " + this.countTilesInRow(3));
      System.out.println("Row 4 (4 fields): Color[" + this.getColorOfTheRow(4) + "] Filled: " + this.countTilesInRow(4));
      System.out.println("Row 5 (5 fields): Color[" + this.getColorOfTheRow(5) + "] Filled: " + this.countTilesInRow(5));
      System.out.println();
    }

    public int countTilesInRow(int row) {
      int counter = 0;
      for (Tile t : this.wallMap.get(row-1)) {
        if (t != null) counter++;
      }
      return counter;
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

      if (this.wallMap.get(row-1)[0] != null) return this.wallMap.get(row-1)[0];
      else return null;
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
      Arrays.fill(wallMap.get(row), null);

    }

}
