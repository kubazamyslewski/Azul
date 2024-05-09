package test.azul;

import main.Exceptions.FirstTileInWorkshopException;
import main.azul.*;
import main.client.GameSession;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

  @Test
  void addTileToFloorShouldAddTileToFloor() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.BLACK);
    game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.YELLOW);
    assertEquals(2, game.getPlayers()[0].getBoard().getFloor().getAmountOfTiles());
  }

  @Test
  void overflowingTheFloorShouldAddTilesToBox() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 20; i++) {
      game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.BLACK);
    }
    assertEquals(13, game.getPlayers()[0].getBoard().getLinkedBox().size());
    for (int i = 0; i < 5; i++) {
      game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.BLACK);
    }
    assertEquals(18, game.getPlayers()[0].getBoard().getLinkedBox().size());
  }

  @Test
  void clearFloorShouldClearTheFloor() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 7; i++) {
      game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getFloor().clearFloor();
    assertEquals(0, game.getPlayers()[0].getBoard().getFloor().getAmountOfTiles());
  }

  @Test
  void clearFloorShouldAddTilesToBox() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 5; i++) {
      game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.BLACK);
    }
    for (int i = 0; i < 2; i++) {
      game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.YELLOW);
    }
    game.getPlayers()[0].getBoard().getFloor().clearFloor();
    assertEquals(7, game.getPlayers()[0].getBoard().getLinkedBox().size());
    int[] tiles = game.getPlayers()[0].getBoard().getLinkedBox().retrieveContents();
    assertEquals(5, tiles[0]);
    assertEquals(2, tiles[3]);
  }

  @Test
  void containsFirstTileShouldReturnTrueIfContains() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    game.getPlayers()[0].getBoard().getFloor().addTileToFloor(Tile.FIRST);
    assertTrue(game.getPlayers()[0].getBoard().getFloor().containsFirstTile());
  }

  @Test
  void containsFirstTileShouldReturnTrueIfDoesNotContain() throws FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    assertFalse(game.getPlayers()[0].getBoard().getFloor().containsFirstTile());
  }

  public GameSession initializeTestGame() throws FirstTileInWorkshopException {
    String input = "2";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);
    GameSession game = new GameSession();
    return game;
  }
}