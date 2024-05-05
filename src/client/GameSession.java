package client;

import Exceptions.FirstTileInWorkshopException;
import azul.*;
import java.util.Scanner;


public class GameSession {

    private int playerCount;

    private Player[] players;

    private TileDrawingPool linkedTileDrawingPool;

    private Bag linkedBag;

    private Box linkedBox;

    Scanner inputHandler = new Scanner(System.in);

    public GameSession() throws FirstTileInWorkshopException {

        do {
            System.out.print("Enter the amount of players (2-4): ");
            playerCount = inputHandler.nextInt();
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
        this.linkedTileDrawingPool.printState();

    }

    public Box getLinkedBox() { return this.linkedBox; }

    public static void main(String[] args) throws FirstTileInWorkshopException {
        GameSession game = new GameSession();
    }
}
