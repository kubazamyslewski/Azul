package main.azul;

import java.io.Serializable;

/**
 * Enum representing types of tiles
 */
public enum Tile implements Serializable {
  FIRST(0),
  BLACK(1),
  WHITE(2),
  RED(3),
  YELLOW(4),
  BLUE(5);

  final int valueOfColor;

  Tile(int valueOfColor) {
    this.valueOfColor = valueOfColor;
  }

  public boolean equals(Tile other) {
    return this.valueOfColor == other.valueOfColor;
  }
}
