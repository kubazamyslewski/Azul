package test.azul;

import main.azul.Box;
import main.azul.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

  @Test
  void addShouldAddTilesToBox() {
    Box box = new Box();
    for (int i = 0; i < 60; i++) {
      box.add(Tile.BLACK);
    }
    for (int i = 0; i < 20; i++) {
      box.add(Tile.YELLOW);
    }
    for (int i = 0; i < 40; i++) {
      box.add(Tile.BLUE);
    }
    assertEquals(120, box.size());
    assertEquals(60, box.getBoxMap().get(Tile.BLACK).size());
    assertEquals(20, box.getBoxMap().get(Tile.YELLOW).size());
    assertEquals(40, box.getBoxMap().get(Tile.BLUE).size());
  }

  @Test
  void sizeShouldReturnSizeOfTheBox() {
    Box box = new Box();
    for (int i = 0; i < 60; i++) {
      box.add(Tile.BLACK);
    }
    for (int i = 0; i < 20; i++) {
      box.add(Tile.YELLOW);
    }
    for (int i = 0; i < 40; i++) {
      box.add(Tile.BLUE);
    }
    assertEquals(120, box.size());
    box.add(Tile.YELLOW);
    box.add(Tile.RED);
    assertEquals(122, box.size());
  }

  @Test
  void retrieveContentsShouldReturnProperContentsArray() {
    Box box = new Box();
    for (int i = 0; i < 60; i++) {
      box.add(Tile.BLACK);
    }
    for (int i = 0; i < 20; i++) {
      box.add(Tile.YELLOW);
    }
    for (int i = 0; i < 40; i++) {
      box.add(Tile.BLUE);
    }
    int[] expectedArray = new int[]{60, 0, 0, 20, 40};
    int[] actualArray = box.retrieveContents();
    for (int i = 0; i < 5; i++) {
      assertEquals(expectedArray[i], actualArray[i]);
    }
  }

}