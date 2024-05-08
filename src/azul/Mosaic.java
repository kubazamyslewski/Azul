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
      *
      */
    public void printMosaic() {
      System.out.println("Mosaic:");
      System.out.println("[1 - BLACK, 2 - WHITE, 3 - RED, 4 - YELLOW, 5 - BLUE]");
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (this.mosaic[i][j] != null) System.out.print(this.mosaic[i][j].valueOfColor + " ");
          else System.out.print("0" + " ");
        }
        System.out.println();
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
      this.parentBoard.getOwner().getGame().getScore().scoreNewTile(this.parentBoard.getOwner(), color, row);
    }

    /**
     * Checks if any row on the mosaic is completed.
     * @return - Boolean value indicating if any row on the mosaic is completed.
     */
    public boolean isRowCompleted(){
      return countCompletedRows() > 0;
    }

    /**
     * Checks how many rows have been completed
     * @return number of completed rows
     */
    public int countCompletedRows() {
        boolean[][] check = checkMosaic();
        int completedRows = 0;
        for (int i = 0; i < 5; i++) {
          boolean completionFactor = true;
          for (int j = 0; j < 5; j++) {
            if (!check[i][j]) {
              completionFactor = false;
              break;
            }
          }
          if (completionFactor) {
            completedRows++;
          }
        }
        return completedRows;
      }

    /**
     * Checks how many columns have been completed
     * @return number of completed columns
     */
    public int countCompletedColumns() {
        boolean[][] check = checkMosaic();
        int completedColumns = 0;
        for (int j = 0; j < 5; j++) {
          boolean completionFactor = true;
          for (int i = 0; i < 5; i++) {
            if (!check[i][j]) {
              completionFactor = false;
              break;
            }
          }
          if (completionFactor) {
            completedColumns++;
          }
        }
        return completedColumns;
      }

    /**
     * Checks whether a selected row of a mosaic contains a tile of a certain color
     * @param color - a color which we're looking for
     * @param row   - number of a row to be checked
     * @return boolean stating whether the row contains a tile of this color
     */
    public boolean checkRowForColor(Tile color, int row) {
        for (int i = 0; i < 5; i++) {
          if (mosaic[row][i] != null && mosaic[row][i].equals(color)) return true;
        }
        return false;
    }



  /**
   * Finds which column a color is in based on the row
   * @param row row in which the tile is placed
   * @param color color of the tile
   * @return column (0-4)
   */
  public int findColumnByColor(int row, Tile color){
      int column = switch (color) {
          case BLACK -> 3;
          case WHITE -> 4;
          case RED -> 2;
          case YELLOW -> 1;
          case BLUE -> 0;
          default -> -1;
      };
        for(int i = 0; i < row; i++){
          column++;
          if(column > 4){
            column = 0;
          }
        }
        return column;
    }

    public int countTouchingTiles(int row, int column) {
      boolean[][] check = checkMosaic();
      int rowCount = 0;
      int columnCount = 0;
      if (!check[row][column]) {
        return 0;
      }
      for (int i = column - 1; i >= 0 && i < column; i++) {
          if(!check[row][i]){
              rowCount++;
          }
          else {
              rowCount = 0;
          }
      }

      for (int i = column + 1; i <= 5 && i > column; i++) {
          if(!check[row][i]){
              rowCount++;
          }
          else {
              break;
          }
      }

      for (int i = row - 1; i >= 0 && i < row; i++) {
          if(!check[i][column]){
              columnCount++;
          }
          else {
              columnCount = 0;
          }
      }

      for (int i = row + 1; i <= 5 && i > row; i++) {
          if(!check[i][column]){
              columnCount++;
          }
          else {
              break;
          }
      }
      return rowCount + columnCount;
    }
}

