package test.azul;

import main.Exceptions.ColorNotInTheMiddleException;
import main.Exceptions.FirstTileInWorkshopException;
import main.Exceptions.WrongTileColourException;
import main.azul.Tile;
import main.client.GameSession;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class MiddleTest {

  @Test
  void addShouldAddTileToMiddle() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.YELLOW);
    assertFalse(game.getLinkedTileDrawingPool().getMiddle().isEmpty());
  }

  @Test
  void colorsEmptyShouldStateTrueIfContainsOnlyFirstTile() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.FIRST);
    assertTrue(game.getLinkedTileDrawingPool().getMiddle().colorsEmpty());
  }

  @Test
  void hasColorShouldStateTrueIfHasColor() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.BLACK);
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.RED);
    assertTrue(game.getLinkedTileDrawingPool().getMiddle().hasColor(Tile.BLACK));
    assertTrue(game.getLinkedTileDrawingPool().getMiddle().hasColor(Tile.RED));
  }

  @Test
  void hasColorShouldStateFalseDoesNotHaveColor() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    assertFalse(game.getLinkedTileDrawingPool().getMiddle().hasColor(Tile.BLACK));
    assertFalse(game.getLinkedTileDrawingPool().getMiddle().hasColor(Tile.RED));
  }

  @Test
  void getTileQuantityShouldReturnSpecifiedColorsQuantity() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.BLACK);
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.BLACK);
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.BLACK);
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.YELLOW);
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.YELLOW);
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.RED);
    assertEquals(3, game.getLinkedTileDrawingPool().getMiddle().getTileQuantity(Tile.BLACK));
    assertEquals(2, game.getLinkedTileDrawingPool().getMiddle().getTileQuantity(Tile.YELLOW));
    assertEquals(1, game.getLinkedTileDrawingPool().getMiddle().getTileQuantity(Tile.RED));
  }

  @Test
  void isEmptyShouldStateTrueIfEmpty() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    assertTrue(game.getLinkedTileDrawingPool().getMiddle().isEmpty());
  }

  @Test
  void isEmptyShouldStateFalseIfNotEmpty() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    game.getLinkedTileDrawingPool().getMiddle().add(Tile.FIRST);
    assertFalse(game.getLinkedTileDrawingPool().getMiddle().isEmpty());
  }

  @Test
  void getTileColorFromMiddleShouldAddTilesToPlayersWall() throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 3; i++) {
      game.getLinkedTileDrawingPool().getMiddle().add(Tile.BLACK);
    }
    game.getLinkedTileDrawingPool().getMiddle().getTileColorFromMiddle(game.getPlayers()[0],Tile.BLACK,4);
    Tile[] row = game.getPlayers()[0].getBoard().getWall().getWallMap().get(4);
    for (int i = 0; i < 3; i++) {
      assertEquals(Tile.BLACK,row[i]);
    }
  }

  @Test
  void getTileColorFromMiddleShouldClearThisColorFromMiddle() throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 5; i++) {
      game.getLinkedTileDrawingPool().getMiddle().add(Tile.YELLOW);
    }
    game.getLinkedTileDrawingPool().getMiddle().getTileColorFromMiddle(game.getPlayers()[0],Tile.YELLOW,4);
    assertTrue(game.getLinkedTileDrawingPool().getMiddle().isEmpty());
  }

  public GameSession initializeTestGame() throws FirstTileInWorkshopException {
    String input = "2";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);
    GameSession game = new GameSession();
    return game;
  }

}