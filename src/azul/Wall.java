package azul;

import Exceptions.WrongTileColourException;

import java.util.Arrays;
import java.util.Stack;

/**
 * Class representing the area where players put tiles before putting them on mosaic
 */
public class Wall {

    Board parentBoard;
    Box linkedBox;
    Mosaic linkedMosaic;

    private Tile[] firstRow;
    private Tile[] secondRow;
    private Tile[] thirdRow;
    private Tile[] fourthRow;
    private Tile[] fifthRow;
    private final Stack<Tile> tempStorage = new Stack<>();

    public Wall(Board parentBoard, Box linkedBox, Mosaic linkedMosaic){
      this.initializeWall(parentBoard, linkedBox, linkedMosaic);
    }

    /**
    * Initializes the wall.
    */
    private void initializeWall(Board parentBoard, Box linkedBox, Mosaic linkedMosaic) {
      this.parentBoard = parentBoard;
      this.linkedBox = linkedBox;
      this.linkedMosaic = linkedMosaic;
      firstRow = new Tile[1];
      secondRow = new Tile[2];
      thirdRow = new Tile[3];
      fourthRow = new Tile[4];
      fifthRow = new Tile[5];
    }

    //TODO: z tej metody pobierać kafelki do addTilesToWall
    public void addToTempStorage(Tile tile){
        tempStorage.push(tile);
    }

    /**
     * Returns the color of tiles arranged in a specific row on the wall.
     * @param row - Row number from 1 to 5
     * @return - Tile representing the color of the row.
     */
    public Tile getColorOfTheRow(int row){
      switch(row) {
        case 1:
          if (firstRow[0] != null) return this.firstRow[0];
          else return null;
        case 2:
          if (secondRow[0] != null) return this.secondRow[0];
          else return null;
        case 3:
          if (thirdRow[0] != null) return this.thirdRow[0];
          else return null;
        case 4:
          if (fourthRow[0] != null) return this.fourthRow[0];
          else return null;
        case 5:
          if (fifthRow[0] != null) return this.fifthRow[0];
          else return null;
        default:
          throw new IllegalArgumentException("No such row on the wall!");
      }
    }

    /**
     * Generates a 5x5 table representing the wall state (empty spot = null).
     * @return - 5x5 table representing the wall state.
     */
    public Tile[][] exportWall() {
        return null;
    }

    /**
     * Adds tiles to a row on the wall or to the floor if the row becomes full.
     * Checks whether it is possible to place certain colour of tiles on a specified row
     * and throws an exception otherwise.
     * @param row   - Row number.
     */
    public void addTilesToWall(int row, Mosaic mosaic) throws WrongTileColourException{
      Tile colour;
      Tile[] tilesToAdd = new Tile[tempStorage.size()];
      for (int i = 0; i < tempStorage.size(); i++) {
        tilesToAdd[i] = tempStorage.pop();
      }

      Tile[] modifiedRow = switch (row) {
        case 1 -> this.firstRow;
        case 2 -> this.secondRow;
        case 3 -> this.thirdRow;
        case 4 -> this.fourthRow;
        case 5 -> this.fifthRow;
        default -> throw new IllegalArgumentException("No such row on the wall!");
      };

      colour = modifiedRow[0];
      if (((colour != null) && (!tilesToAdd[0].equals(colour))) || (mosaic.checkRowForColor(row, tilesToAdd[0])))
        throw new WrongTileColourException("You cannot put this color in this row!");

      int tilesToAddIndex = 0;
      for (int i = 0; i < modifiedRow.length; i++) {
        if((modifiedRow[i] == null) && tilesToAddIndex < tilesToAdd.length) {
          modifiedRow[i] = tilesToAdd[tilesToAddIndex];
          tilesToAddIndex++;
        }
      }
      while (tilesToAddIndex < tilesToAdd.length) {
        this.parentBoard.getFloor().addTileToFloor(tilesToAdd[tilesToAddIndex]);
        tilesToAddIndex++;
      }
    }

    /**
     * Pushes tiles from the wall to the mosaic.
     */
    public void checkWallAndPushToMosaic(Box box, Mosaic mosaic) throws WrongTileColourException {
      for (int i = 1; i <= 5; i++) {
        if (checkRow(i)) pushRowToMosaic(i, box, mosaic);
      }
    }

    private boolean checkRow(int row) {
      return switch (row) {
        case 1 -> {
          for (Tile t : firstRow) {
            if (t == null) yield false;
          }
          yield true;
        }
        case 2 -> {
          for (Tile t : secondRow) {
            if (t == null) yield false;
          }
          yield true;
        }
        case 3 -> {
          for (Tile t : thirdRow) {
            if (t == null) yield false;
          }
          yield true;
        }
        case 4 -> {
          for (Tile t : fourthRow) {
            if (t == null) yield false;
          }
          yield true;
        }
        case 5 -> {
          for (Tile t : fifthRow) {
            if (t == null) yield false;
          }
          yield true;
        }
        default -> false;
      };
    }

    /**
    * pushes certain row to the mosaic
    */
    private void pushRowToMosaic(int row, Box box, Mosaic mosaic) throws WrongTileColourException, IllegalArgumentException{
      Tile color = switch(row) {
        case 1 -> firstRow[0];
        case 2 -> secondRow[0];
        case 3 -> thirdRow[0];
        case 4 -> fourthRow[0];
        case 5 -> fifthRow[0];
        default -> throw new IllegalArgumentException("No such row exists!");
      };
      mosaic.setTile(row, color);
      clearRowOnWall(row, box);
    }

    /**
    * Clears specific row on the wall.
    * @param row - Row number to be cleared.
    * @param box - Box to add the removed tiles to.
    */
    private void clearRowOnWall(int row, Box box) {
      Tile color;
      switch(row) {
        case 1:
          color = firstRow[0];
          for (int i = 0; i < 1; i++) {
            box.add(color);
          }
          Arrays.fill(firstRow, null);
          break;
        case 2:
          color = secondRow[0];
          for (int i = 0; i < 2; i++) {
            box.add(color);
          }
          Arrays.fill(secondRow, null);
          break;
        case 3:
          color = thirdRow[0];
          for (int i = 0; i < 3; i++) {
            box.add(color);
          }
          Arrays.fill(thirdRow, null);
          break;
        case 4:
          color = fourthRow[0];
          for (int i = 0; i < 4; i++) {
            box.add(color);
          }
          Arrays.fill(fourthRow, null);
          break;
        case 5:
          color = fifthRow[0];
          for (int i = 0; i < 5; i++) {
            box.add(color);
          }
          Arrays.fill(fifthRow, null);
          break;
      }
    }


    /**
     * Calculates the number of free spots in a specific row on the wall.
     * @param rowNumber - Row number.
     * @return - Number of free spots in the row.
     */
    public int freeSpotsInRow(int rowNumber) {

        return 0;
    }
}
