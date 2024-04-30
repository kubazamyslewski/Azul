package azul;

import Exceptions.WrongTileColourException;

/**
 * Class representing the area where players put tiles before putting them on mosaic
 */
public class Wall {

    Board parentBoard;

    private Tile[] firstRow;
    private Tile[] secondRow;
    private Tile[] thirdRow;
    private Tile[] fourthRow;
    private Tile[] fifthRow;

    public Wall(Board parentBoard){
      this.parentBoard = parentBoard;
      firstRow = new Tile[1];
      secondRow = new Tile[2];
      thirdRow = new Tile[3];
      fourthRow = new Tile[4];
      fifthRow = new Tile[5];
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
     * @param tilesToAdd - Array of tiles to be added.
     */
    public void addTilesToWall(int row, Tile[] tilesToAdd) throws WrongTileColourException{
      Tile colour;
      Tile[] modifiedRow = switch (row) {
        case 1 -> this.firstRow;
        case 2 -> this.secondRow;
        case 3 -> this.thirdRow;
        case 4 -> this.fourthRow;
        case 5 -> this.fifthRow;
        default -> throw new IllegalArgumentException("No such row on the wall!");
      };
      colour = modifiedRow[0];
      if ((colour != null) && (!tilesToAdd[0].equals(colour)))
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
      }
    }

    /**
     * Clears specific row on the wall.
     * @param row - Row number to be cleared.
     */
    private void clearRowOnWall(int row) {

    }

    /**
     * Pushes tiles from the wall to the mosaic.
     */
    void pushWallToMosaic() {
    }

    /**
     * Initializes the wall.
     */
    private void initializeWall() {
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
