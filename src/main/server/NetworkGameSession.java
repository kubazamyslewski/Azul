package main.server;

import main.Exceptions.*;
import main.azul.*;

import java.io.Serializable;
import java.util.Scanner;

public class NetworkGameSession implements Serializable {

  private int playerCount;

  private boolean isOver = false;

  private Player[] players;

  private TileDrawingPool linkedTileDrawingPool;

  private Bag linkedBag;

  private Box linkedBox;

  private Score score;

  public NetworkGameSession(int amountOfPlayers) throws FirstTileInWorkshopException {

    this.playerCount = amountOfPlayers;

    this.linkedBox = new Box();
    this.linkedBag = new Bag(this.linkedBox, 20, 20, 20, 20, 20);
    this.linkedTileDrawingPool = new TileDrawingPool(this.linkedBag, this.playerCount);

    this.players = new Player[playerCount];
    for (int i = 0; i < players.length; i++) {
      this.players[i] = new Player(this);
    }
    this.score = new Score(this.players);

    System.out.println("Game setup complete.");
    System.out.println("Player count: " + playerCount);
    System.out.println();
  }

  public NetworkGameSession (NetworkGameSession n) {
    this.playerCount = n.playerCount;
    this.isOver = n.isOver;
    this.players = n.players;
    this.linkedTileDrawingPool = n.linkedTileDrawingPool;
    this.linkedBag = n.linkedBag;
    this.linkedBox = n.linkedBox;
    this.score = n.score;
  }

  public Score getScore() { return this.score; }

  public boolean getIsOver() { return this.isOver; }

  public Box getLinkedBox() { return this.linkedBox; }

  public TileDrawingPool getLinkedTileDrawingPool() { return this.linkedTileDrawingPool; }

  public int getPlayerCount() { return playerCount; }

  public Bag getLinkedBag() { return this.linkedBag; }

  public Player[] getPlayers(){return this.players;}

  public void checkIfOver() {
    for (Player player : this.players) {
      boolean isFinished = player.getBoard().getMosaic().isRowCompleted();
      if (isFinished) this.isOver = true;
    }
  }
}
