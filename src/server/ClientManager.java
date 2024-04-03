package server;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class used to manage client's connection to a server.
 */
public class ClientManager implements Runnable {
    private int playerNumber;
    private Server server;
    private Socket client;
    private PrintWriter output;
    private ServerCommunication serverCommunication;
    public ClientManager(Socket client, int playerNumber, Server server){

    }

    /**
     * Sends message to  server
     * @param message
     */
    public void sendMessage(String message){

    }
    public void disconnectClient(){

    }

    /**
     * Informs client about starting turn.
     */
    public void startTurn(){

    }

    /**
     * Informs client about end of turn.
     */
    public void endTurn(){

    }

    /**
     * Informs client about accepted move
     */
    public void ok(){

    }

    /**
     * Informs client about rejected move.
     */
    public void wrong(){

    }
    @Override
    public void run(){

    }
}
