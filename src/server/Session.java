package server;

import azul.*;
import client.Client;

/**
 * Class representing current state of the game on the server.
 */
public class Session {
    private int turnCounter;
    private int numberOfPlayers;
    private int nextRoundFirstPlayer;
    private Bag bag;
    private TileDrawingPool tileDrawingPool;
    private Box box;
    private Player[] players;
    private Server server;

    public int getTurnCounter() {
        return turnCounter;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getNextRoundFirstPlayer() {
        return nextRoundFirstPlayer;
    }
    public void setNextRoundFirstPlayer(int nextRoundFirstPlayer){
        this.nextRoundFirstPlayer = nextRoundFirstPlayer;
    }

    public Bag getBag() {
        return bag;
    }

    public TileDrawingPool getTileDrawingPool() {
        return tileDrawingPool;
    }

    public Box getBox() {
        return box;
    }

    public Player getPlayer(int playerNumber){
        return players[playerNumber];
    }




    /**
     * Creates a game session on existing server
     * @param numberOfPlayers
     * @param server existing server for a session to be hosted on
     */
    public Session(int numberOfPlayers, Server server){

    }

    /**
     * Creates a game session for a local gameplay.
     * @param numberOfPlayers
     */
    public Session(int numberOfPlayers){

    }

    /**
     * Updates board to its current state.
     */
    public void updateBoard(){

    }
}
