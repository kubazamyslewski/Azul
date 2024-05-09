package test.azul;

import main.azul.Bag;
import main.azul.Box;
import main.azul.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

  @Test
  void addToBagShouldAddToBag() {
    Bag bag = new Bag(new Box(), 0, 0, 0, 0, 0);
    bag.addToBag(Tile.BLACK, 10);
    bag.addToBag(Tile.YELLOW, 50);
    assertEquals(10, bag.getBagMap().get(Tile.BLACK).size());
    assertEquals(50, bag.getBagMap().get(Tile.YELLOW).size());
    assertEquals(60, bag.size());
  }

  @Test
  void refillTheBagFromTheBoxShouldRefillTheBag() {
    Box box = new Box();
    Bag bag = new Bag(box, 0, 0, 0, 0, 0);
    for (int i = 0; i < 60; i++) {
      box.add(Tile.BLACK);
    }
    for (int i = 0; i < 20; i++) {
      box.add(Tile.YELLOW);
    }
    for (int i = 0; i < 40; i++) {
      box.add(Tile.RED);
    }
    bag.refillTheBagFromTheBox();
    assertEquals(60, bag.getBagMap().get(Tile.BLACK).size());
    assertEquals(20, bag.getBagMap().get(Tile.YELLOW).size());
    assertEquals(40, bag.getBagMap().get(Tile.RED).size());
  }

  @Test
  void sizeShouldReturnCorrectSize() {
    Box box = new Box();
    Bag bag = new Bag(box, 20, 10, 5, 2, 1);
    assertEquals(38, bag.size());
    bag.addToBag(Tile.BLACK, 22);
    bag.addToBag(Tile.YELLOW, 30);
    assertEquals(90, bag.size());
  }

  @Test
  void popShouldDrawRandomTileFromBag() {
    Box box = new Box();
    Bag bag = new Bag(box, 20, 20, 20, 20, 20);
    Tile popped = bag.pop();
    assertTrue(popped.equals(Tile.BLACK) || popped.equals(Tile.WHITE) || popped.equals(Tile.RED) || popped.equals(Tile.YELLOW) || popped.equals(Tile.BLUE));
    assertEquals(99, bag.size());
  }
}