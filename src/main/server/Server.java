package main.server;
import main.Exceptions.*;
import main.azul.Player;
import main.azul.Tile;
import main.azul.Workshop;
import main.client.Move;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int playersReady = 0;
    private ServerSocket server;
    public static final int PORT = 3030;
    public static final String DISCONNECT_MESSAGE = "DISCONNECT";
    private List<ClientHandler> clientHandlers;
    private int playerOnTurn;
    private int numberOfPlayers=0;
    private int index = -1;
    private NetworkGameSession gameSession;

    /**
     *
     */
    public Server(){
        clientHandlers = new ArrayList<>();
        playerOnTurn = 0;
        try{
            server = new ServerSocket(PORT);
            while(true) {
                initConnections();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newPlayerReady() { this.playersReady++; }

    public int getPlayersReady() { return this.playersReady; }

    private void initConnections() throws IOException {
        if (numberOfPlayers<3){
            Socket clientSocket = server.accept();
            if(clientSocket.isConnected())
                new Thread(()->{
                    numberOfPlayers ++;
                    index++;
                    ClientHandler client = new ClientHandler(clientSocket, index, this);
                    clientHandlers.add(client);
                    client.readMessages();
                }).start();
        }
    }

    public void startGame() throws FirstTileInWorkshopException, IncorrectAmountOfTilesOnFloorException, WrongTileColourException {
        this.gameSession = new NetworkGameSession(numberOfPlayers);

        while (!gameSession.getIsOver()) {
            runRound();
            this.gameSession.checkIfOver();
            if (!this.gameSession.getIsOver()) {
                setupNewRound();
            }

        }
        applyFinishingScore();
        //TODO print final scoreboard to clients
    }

    public void runRound() throws FirstTileInWorkshopException, IncorrectAmountOfTilesOnFloorException, WrongTileColourException {
        providersOfferPhase();
        mosaicLayingPhase();
    }

    private void providersOfferPhase() throws FirstTileInWorkshopException {
        //TODO print providersOfferPhase to clients

        //TODO find the client with first tile

        this.gameSession.getLinkedTileDrawingPool().getMiddle().add(Tile.FIRST);

        do {
            if(this.gameSession.getLinkedTileDrawingPool().isEmpty()) {
                break;
            }
            this.clientHandlers.get(playerOnTurn).callPlayer(this.gameSession);
            nextPlayer();
        } while (true);

    }

    private void mosaicLayingPhase() throws WrongTileColourException, IncorrectAmountOfTilesOnFloorException {

        //TODO print mosaicLayingPhase to clients

        for (Player player : this.gameSession.getPlayers()) {
            player.getBoard().getWall().checkWallAndPushToMosaic();
            this.gameSession.getScore().applyFloorPenalty(player);
        }
        System.out.println();

        //TODO print scoreboard to clients

        System.out.println();
    }

    public void setupNewRound() throws FirstTileInWorkshopException {
        for (Workshop w : this.gameSession.getLinkedTileDrawingPool().getWorkshops()) {
            w.refill();
        }
    }

    public void applyFinishingScore() {
        for (Player player : this.gameSession.getPlayers()) {
            this.gameSession.getScore().scoreColumns(player);
            this.gameSession.getScore().scoreRows(player);
            this.gameSession.getScore().scoreFullColors(player);
        }
    }

    public void processMove(Move m) throws ColorNotInTheMiddleException, ColorNotInWorkshopException, FirstTileInWorkshopException, WrongTileColourException {
        String workshopOrMiddle = m.getWorkshopOrMiddle();
        switch(workshopOrMiddle) {
            case "workshop":
                this.gameSession.getLinkedTileDrawingPool().getWorkshops()[m.getChosenWorkshop()-1].getTileColorFromWorkshop(gameSession.getPlayers()[m.getPerformer()], m.getColor(),m.getRow());
                break;
            case "middle":
                this.gameSession.getLinkedTileDrawingPool().getMiddle().getTileColorFromMiddle(this.gameSession.getPlayers()[m.getPerformer()], m.getColor(), m.getRow());
                break;
        }
    }

    //TODO: zmiana tego booleana w rzeczywistym kliencie gry
    public void nextPlayer(){
        //int playerThatWasOnTurn = playerOnTurn;
        playerOnTurn = (playerOnTurn + 1) % numberOfPlayers;
        for (ClientHandler client : clientHandlers){

        }
    }
    public int getPlayerOnTurn(){
        return playerOnTurn;
    }




    public static void main(String[] args) {
        new Server();
    }
}
