package azul;

/**
 * Enum representing types of tiles
 */
public enum Tile {
  BLACK(1),
  WHITE(2),
  RED(3),
  YELLOW(4),
  BLUE(5);

  int valueOfColour;

  Tile(int valueOfColour) {
    this.valueOfColour = valueOfColour;
  }
}
