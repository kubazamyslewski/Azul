package client;

import Exceptions.*;
import azul.*;
import java.util.Scanner;


public class GameSession {

    private int playerCount;

    private boolean isOver = false;

    private Player[] players;

    private TileDrawingPool linkedTileDrawingPool;

    private Bag linkedBag;

    private Box linkedBox;

    private Scanner inputHandler = new Scanner(System.in);

    private Score score = new Score(this.getPlayers());

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

    public Score getScore() { return this.score; }

    public Scanner getInputHandler() { return this.inputHandler; }

    public Box getLinkedBox() { return this.linkedBox; }

    public TileDrawingPool getLinkedTileDrawingPool() { return this.linkedTileDrawingPool; }

    public int getPlayerCount() { return playerCount; }

    public Player[] getPlayers(){return this.players;}

    public int getIndexFromPlayerID(int playerID) {
        for (int i = 0; i < players.length; i++) {
            if(players[i].getPlayerID() == playerID){
                return i;
            }
        }
        return -1;
    }

    public void runRound() throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException, ColorNotInWorkshopException, IncorrectAmountOfTilesOnFloorException {
        providersOfferPhase();
        mosaicLayingPhase();
    }

    private void providersOfferPhase() throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException, ColorNotInWorkshopException {

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

    private void mosaicLayingPhase() throws WrongTileColourException, IncorrectAmountOfTilesOnFloorException {

        System.out.println("------- Start of the mosaic laying phase --------");

        for (Player player : this.players) {
            player.getBoard().getWall().checkWallAndPushToMosaic();
            score.applyFloorPenalty(player);
        }

        System.out.println("CurrentScoreBoard:");
        for (Player player : this.players) {
            System.out.println("Player " + player.getPlayerID() + ": " + player.getPlayerScore() + " points;");
        }

        System.out.println();
    }

    public void checkIfOver() {
        for (Player player : this.players) {
            boolean isFinished = player.getBoard().getMosaic().isRowCompleted();
            if (!isFinished) continue;
            else this.isOver = true;
        }
    }

    public void applyFinishingScore() {
        for (Player player : this.players) {
            score.scoreColumns(player);
            score.scoreRows(player);
            score.scoreFullColors(player);
        }
    }

    public void setupNewRound() throws FirstTileInWorkshopException {

        for (Workshop w : this.linkedTileDrawingPool.getWorkshops()) {
            w.refill();
        }
    }



    public static void main(String[] args) throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException, ColorNotInWorkshopException, IncorrectAmountOfTilesOnFloorException {
        GameSession game = new GameSession();

        while (!game.isOver) {
            game.runRound();
            game.checkIfOver();
            if (!game.isOver) {
                game.setupNewRound();
            }

        }
        game.applyFinishingScore();

    }

    //TODO add needed checks to cli
    //TODO fix pushing rows to mosaic
    //TODO further debugging
}
