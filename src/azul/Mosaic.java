package azul;

/**
 * Mosaic representation.
 */
public class Mosaic {

    /**
     * A two-dimensional array representing the square mosaic
     */
    private Tile[][] mosaic;

    public Mosaic() {
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
     * @param column - Column index.
     * @param tile   - The tile to be placed.
     */
    public void setTile(int row, int column, Tile tile) {
      this.mosaic[row][column] = tile;
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
        if (completionFactor) return completionFactor;
      }
      return false;
    }
}

