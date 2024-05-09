package main.server;

import main.Exceptions.*;
import main.azul.*;

import java.util.Scanner;

public class NetworkGameSession {

  private int playerCount;

  private boolean isOver = false;

  private Player[] players;

  private TileDrawingPool linkedTileDrawingPool;

  private Bag linkedBag;

  private Box linkedBox;

  private Scanner inputHandler = new Scanner(System.in);

  private Score score = new Score(this.players);

  public NetworkGameSession(int amountOfPlayers) throws FirstTileInWorkshopException {

    do {
      this.playerCount = amountOfPlayers;
      if (playerCount < 2 || playerCount > 4) {
        System.out.println("Incorrect number of players!");
      }
    } while (playerCount < 2 || playerCount > 4);

    this.linkedBox = new Box();
    this.linkedBag = new Bag(this.linkedBox, 20, 20, 20, 20, 20);
    this.linkedTileDrawingPool = new TileDrawingPool(this.linkedBag, this.playerCount);

    this.players = new Player[playerCount];
    for (int i = 0; i < players.length; i++) {
      this.players[i] = new Player(this);
    }

    System.out.println("Game setup complete.");
    System.out.println("Player count: " + playerCount);
    System.out.println();

  }
}
