package test.azul;

import main.Exceptions.FirstTileInWorkshopException;
import main.Exceptions.WrongTileColourException;
import main.azul.Tile;
import main.client.GameSession;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

  @Test
  void countTilesInRowShouldReturnNumberOfTilesInRow() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(4);
    for (int i = 0; i < 1; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.YELLOW);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(2);
    assertEquals(4, game.getPlayers()[0].getBoard().getWall().countTilesInRow(5));
    assertEquals(1, game.getPlayers()[0].getBoard().getWall().countTilesInRow(3));
  }

  @Test
  void addToTempStorageShouldAddToTempStorage() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    assertFalse(game.getPlayers()[0].getBoard().getWall().getTempStorage().isEmpty());
  }

  @Test
  void getColorOfTheRowShouldReturnColorOfTheRow() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(2);
    assertEquals(Tile.BLACK, game.getPlayers()[0].getBoard().getWall().getColorOfTheRow(3));
  }

  @Test
  void checkIfRowIsSafeForColorShouldReturnTrueIfSafe() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(2);
    assertTrue(game.getPlayers()[0].getBoard().getWall().checkIfRowIsSafeForColor(2,Tile.BLACK));
  }

  @Test
  void checkIfRowIsSafeForColorShouldReturnFalseIfNotSafe() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(2);
    assertFalse(game.getPlayers()[0].getBoard().getWall().checkIfRowIsSafeForColor(2,Tile.RED));
  }

  @Test
  void addTilesToWallShouldAddTilesToCorrectRowsOfTheWall() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(0);
    for (int i = 0; i < 1; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.YELLOW);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(2);
    for (int i = 0; i < 3; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.RED);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(4);
    Tile[] expectedRowOne = new Tile[]{Tile.BLACK};
    Tile[] expectedRowThree = new Tile[]{Tile.YELLOW, null, null};
    Tile[] expectedRowFive = new Tile[]{Tile.RED,Tile.RED,Tile.RED,null,null};
    for (int i = 0; i < 1; i++) {
      assertEquals(expectedRowOne[i], game.getPlayers()[0].getBoard().getWall().getWallMap().get(0)[i]);
    }
    for (int i = 0; i < 3; i++) {
      assertEquals(expectedRowThree[i], game.getPlayers()[0].getBoard().getWall().getWallMap().get(2)[i]);
    }
    for (int i = 0; i < 5; i++) {
      assertEquals(expectedRowFive[i], game.getPlayers()[0].getBoard().getWall().getWallMap().get(4)[i]);
    }
  }

  @Test
  void overflowingRowsShouldFillTheFloor() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(0);
    Tile[] expectedFloor = new Tile[]{Tile.BLACK, Tile.BLACK, Tile.BLACK, null, null, null, null};
    for (int i = 0; i < 7; i++) {
      assertEquals(expectedFloor[i], game.getPlayers()[0].getBoard().getFloor().getTilesOnTheFloor()[i]);
    }
  }

  @Test
  void checkWallAndPushToMosaicShouldPushTilesFromFullRowsToMosaic() throws FirstTileInWorkshopException, WrongTileColourException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(0);
    for (int i = 0; i < 8; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.YELLOW);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(4);
    game.getPlayers()[0].getBoard().getWall().checkWallAndPushToMosaic();
    assertEquals(Tile.BLACK, game.getPlayers()[0].getBoard().getMosaic().getPlacedTile(0, 3));
    assertEquals(Tile.YELLOW, game.getPlayers()[0].getBoard().getMosaic().getPlacedTile(4, 0));
  }

  @Test
  void checkWallAndPushToMosaicShouldClearRows() throws WrongTileColourException, FirstTileInWorkshopException {
    GameSession game = initializeTestGame();
    for (int i = 0; i < 4; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.BLACK);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(0);
    for (int i = 0; i < 8; i++) {
      game.getPlayers()[0].getBoard().getWall().addToTempStorage(Tile.YELLOW);
    }
    game.getPlayers()[0].getBoard().getWall().addTilesToWall(4);
    game.getPlayers()[0].getBoard().getWall().checkWallAndPushToMosaic();
    Tile[] expectedRow1 = new Tile[]{null};
    Tile[] expectedRow5 = new Tile[]{null, null, null, null, null};
    for (int i = 0; i < 1; i++) {
      assertEquals(expectedRow1[i], game.getPlayers()[0].getBoard().getWall().getWallMap().get(0)[i]);
    }
    for (int i = 0; i < 5; i++) {
      assertEquals(expectedRow5[i], game.getPlayers()[0].getBoard().getWall().getWallMap().get(4)[i]);
    }
  }

  public GameSession initializeTestGame() throws FirstTileInWorkshopException {
    String input = "2";
    ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    System.setIn(inputStream);
    GameSession game = new GameSession();
    return game;
  }

}