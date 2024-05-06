package client;

import Exceptions.ColorNotInTheMiddleException;
import Exceptions.FirstTileInWorkshopException;
import Exceptions.WrongTileColourException;
import azul.*;
import java.util.Scanner;


public class GameSession {

    private int playerCount;

    private Player[] players;

    private TileDrawingPool linkedTileDrawingPool;

    private Bag linkedBag;

    private Box linkedBox;

    private Scanner inputHandler = new Scanner(System.in);

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

    }

    public Scanner getInputHandler() { return this.inputHandler; }

    public Box getLinkedBox() { return this.linkedBox; }

    public TileDrawingPool getLinkedTileDrawingPool() { return this.linkedTileDrawingPool; }

    public int getPlayerCount() { return playerCount; }

    public void runRound() throws FirstTileInWorkshopException {
        providersOfferPhase();
    }

    private void providersOfferPhase() throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException {

        System.out.println("------- Start of the providers offer phase --------");

        this.linkedTileDrawingPool.printState();

        this.linkedTileDrawingPool.getMiddle().add(Tile.FIRST);
        boolean isEmpty = false;
        do {
            for (Player player : this.players) {
                if(this.linkedTileDrawingPool.isEmpty()) {
                    isEmpty = true;
                    break;
                }
                player.takeTile();
            }
        } while (!isEmpty);

    }



    public static void main(String[] args) throws FirstTileInWorkshopException {
        GameSession game = new GameSession();
        game.runRound();
    }
}
