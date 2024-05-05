package azul;

import Exceptions.WrongTileColourException;

/**
 * Mosaic representation.
 */
public class Mosaic {

    /**
     * A two-dimensional array representing the square mosaic
     */
    private final Tile[][] mosaic;
    private final Board parentBoard;

    public Mosaic(Board parentBoard) {
      this.parentBoard = parentBoard;
      this.mosaic = new Tile[5][5];
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          this.mosaic[i][j] = null;
        }
      }
    }

    /**
     * Converts the mosaic to a boolean matrix of size 5x5 to check where tiles are placed.
     * @return two-dimensional boolean array stating where the Tiles are on the mosaic
     */
    public boolean[][] checkMosaic(){
      boolean[][] mosaicFilling = new boolean[5][5];
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          mosaicFilling[i][j] = this.mosaic[i][j] != null;
        }
      }
      return mosaicFilling;
    }

    /**
     * Gets the placed tile on a specific mosaic square.
     * @param row    - Row index.
     * @param column - Column index.
     * @return - The placed tile.
     */
    public Tile getPlacedTile(int row, int column) {
        return mosaic[row][column];
    }

    /**
     * Sets the tile on a specific mosaic square.
     * @param row    - Row index.
     * @param color   - The tile to be placed.
     */
    public void setTile(int row, Tile color) throws WrongTileColourException, IllegalArgumentException {
      switch(row) {
        case 1:
          switch(color) {
            case BLACK:
              this.mosaic[row][3] = Tile.BLACK;
              break;
            case WHITE:
              this.mosaic[row][4] = Tile.WHITE;
              break;
            case RED:
              this.mosaic[row][2] = Tile.RED;
              break;
            case YELLOW:
              this.mosaic[row][1] = Tile.YELLOW;
              break;
            case BLUE:
              this.mosaic[row][0] = Tile.BLUE;
              break;
            default:
              throw new WrongTileColourException("This tile color cannot be placed on the mosaic!");
          } break;
        case 2:
          switch(color) {
            case BLACK:
              this.mosaic[row][4] = Tile.BLACK;
              break;
            case WHITE:
              this.mosaic[row][0] = Tile.WHITE;
              break;
            case RED:
              this.mosaic[row][3] = Tile.RED;
              break;
            case YELLOW:
              this.mosaic[row][2] = Tile.YELLOW;
              break;
            case BLUE:
              this.mosaic[row][1] = Tile.BLUE;
              break;
            default:
              throw new WrongTileColourException("This tile color cannot be placed on the mosaic!");
          } break;
        case 3:
          switch(color) {
            case BLACK:
              this.mosaic[row][0] = Tile.BLACK;
              break;
            case WHITE:
              this.mosaic[row][1] = Tile.WHITE;
              break;
            case RED:
              this.mosaic[row][4] = Tile.RED;
              break;
            case YELLOW:
              this.mosaic[row][3] = Tile.YELLOW;
              break;
            case BLUE:
              this.mosaic[row][2] = Tile.BLUE;
              break;
            default:
              throw new WrongTileColourException("This tile color cannot be placed on the mosaic!");
          } break;
        case 4:
          switch(color) {
            case BLACK:
              this.mosaic[row][1] = Tile.BLACK;
              break;
            case WHITE:
              this.mosaic[row][2] = Tile.WHITE;
              break;
            case RED:
              this.mosaic[row][0] = Tile.RED;
              break;
            case YELLOW:
              this.mosaic[row][4] = Tile.YELLOW;
              break;
            case BLUE:
              this.mosaic[row][3] = Tile.BLUE;
              break;
            default:
              throw new WrongTileColourException("This tile color cannot be placed on the mosaic!");
          } break;
        case 5:
          switch(color) {
            case BLACK:
              this.mosaic[row][2] = Tile.BLACK;
              break;
            case WHITE:
              this.mosaic[row][3] = Tile.WHITE;
              break;
            case RED:
              this.mosaic[row][1] = Tile.RED;
              break;
            case YELLOW:
              this.mosaic[row][0] = Tile.YELLOW;
              break;
            case BLUE:
              this.mosaic[row][4] = Tile.BLUE;
              break;
            default:
              throw new WrongTileColourException("This tile color cannot be placed on the mosaic!");
          } break;
        default:
          throw new IllegalArgumentException("Such row does not exist!");
      }
    }

    /**
     * Checks if a row on the mosaic is completed.
     * @return - Boolean value indicating if a row on the mosaic is completed.
     */
    public boolean isRowCompleted(){
      boolean[][] check = checkMosaic();
      boolean completionFactor;
      for (int i = 0; i < 5; i++) {
        completionFactor = true;
        for (int j = 0; j < 5; j++) {
          if (!check[i][j]) {
            completionFactor = false;
            break;
          }
        }
        if (completionFactor) return true;
      }
      return false;
    }

  /**
   * Checks whether a selected row of a mosaic contains a tile of a certain color
   * @param row - number of a row to be checked
   * @param color - a color for which we're looking for
   * @return boolean stating whether the row contains a tile of this color
   */
  public boolean checkRowForColor(int row, Tile color) {
      for (int i = 0; i < 5; i++) {
        if (mosaic[row][i].equals(color)) return true;
      }
      return false;
    }
}

