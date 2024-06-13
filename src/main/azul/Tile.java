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
  BLUE(5),
  BLANK(6);

  final int valueOfColor;

  Tile(int valueOfColor) {
    this.valueOfColor = valueOfColor;
  }

  public boolean equals(Tile other) {
    return this.valueOfColor == other.valueOfColor;
  }
  public String getColor(){
    return switch (this.valueOfColor) {
      case 0 -> "ONE";
      case 1 -> "BLACK";
      case 2 -> "WHITE";
      case 3 -> "RED";
      case 4 -> "YELLOW";
      case 5 -> "BLUE";
      default -> "BLANK";
    };
  }
}
