package main.client;

import main.Exceptions.*;
import main.azul.*;
import java.util.Scanner;


public class GameSession {

    private int playerCount;

    private boolean isOver = false;

    private Player[] players;

    private TileDrawingPool linkedTileDrawingPool;

    private Bag linkedBag;
    private Player currentPlayer;



    private Box linkedBox;

    private Scanner inputHandler = new Scanner(System.in);

    private Score score = new Score(this.getPlayers());

    public GameSession() {

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
        this.players[0].setStartingPlayer();

        System.out.println("Game setup complete.");
        System.out.println();

    }

    public Score getScore() { return this.score; }

    public Scanner getInputHandler() { return this.inputHandler; }

    public Box getLinkedBox() { return this.linkedBox; }

    public TileDrawingPool getLinkedTileDrawingPool() { return this.linkedTileDrawingPool; }

    public int getPlayerCount() { return playerCount; }

    public Bag getLinkedBag() { return this.linkedBag; }

    public Player[] getPlayers(){return this.players;}

    public int getIndexFromPlayerID(int playerID) {
        for (int i = 0; i < players.length; i++) {
            if(players[i].getPlayerID() == playerID){
                return i;
            }
        }
        return -1;
    }

    private int getStartingPlayerIndex(){
        for(Player player : this.players){
            if(player.isStartingPlayer()){
                return getIndexFromPlayerID(player.getPlayerID());
            }
        }
        return 0;
    }

    public void runRound() throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException, ColorNotInWorkshopException, IncorrectAmountOfTilesOnFloorException {
        this.getLinkedBox().printBoxContents();
        this.getLinkedBag().printBagContents();

        providersOfferPhase();
        mosaicLayingPhase();
    }

    private void providersOfferPhase() throws FirstTileInWorkshopException, ColorNotInTheMiddleException, WrongTileColourException, ColorNotInWorkshopException {

        System.out.println("------- Start of the providers offer phase --------");

        this.linkedTileDrawingPool.printState();

        this.linkedTileDrawingPool.getMiddle().add(Tile.FIRST);
        boolean isEmpty;
        int currentPlayerIndex = getStartingPlayerIndex();
        do {
            currentPlayer = this.players[currentPlayerIndex];
            currentPlayer.takeTile();

            currentPlayerIndex = (currentPlayerIndex + 1) % this.players.length;

            isEmpty = this.linkedTileDrawingPool.isEmpty();
        } while (!isEmpty);

    }

    private void mosaicLayingPhase() throws WrongTileColourException, IncorrectAmountOfTilesOnFloorException {

        System.out.println("------- Start of the mosaic laying phase --------");

        for (Player player : this.players) {
            player.getBoard().getWall().checkWallAndPushToMosaic();
            player.changeStartingPlayer();
            if (player.getBoard().getFloor().containsFirstTile()){
                player.setStartingPlayer();
            }
            score.applyFloorPenalty(player);
        }
        System.out.println();

        System.out.println("CurrentScoreBoard:");
        for (Player player : this.players) {
            System.out.println("Player " + player.getPlayerID() + ": " + player.getPlayerScore() + " points");
        }

        System.out.println();
    }

    public void checkIfOver() {
        for (Player player : this.players) {
            boolean isFinished = player.getBoard().getMosaic().isRowCompleted();
            if (isFinished) this.isOver = true;
        }
    }

    public void applyFinishingScore() {
        for (Player player : this.players) {
            score.scoreColumns(player);
            score.scoreRows(player);
            score.scoreFullColors(player);
        }
        for (Player player : this.players) {
            System.out.println("Final score of Player " + player.getPlayerID() + ": " + player.getPlayerScore());
        }
        System.out.println("Winner: Player "+score.getCurrentWinner().getPlayerID());
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

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void setLinkedTileDrawingPool(TileDrawingPool linkedTileDrawingPool) {
        this.linkedTileDrawingPool = linkedTileDrawingPool;
    }

    public void setLinkedBag(Bag linkedBag) {
        this.linkedBag = linkedBag;
    }

    public void setLinkedBox(Box linkedBox) {
        this.linkedBox = linkedBox;
    }

    public void setInputHandler(Scanner inputHandler) {
        this.inputHandler = inputHandler;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
