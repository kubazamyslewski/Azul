package client;

import azul.Player;
import gui.Interface;
import server.Session;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Class representing client connected to a server as a player in game
 */
public class Client implements Runnable {
    private String host;
    private int port;
    private BufferedReader input;
    private PrintWriter output;
    private Socket socket;
    private Session session;
    private Interface GUI;

    public Client(String host, Interface GUI, int port, Socket socket, Session session, Player player) {
        this.host = host;
        this.port = port;
        this.socket = socket;
        this.session = session;
        this.GUI = GUI;
    }

    /**
     * Updates client's GUI
     */
    public void updateGUI(){

    }

    /**
     * Sends move to server.
     */
    public void sendMove(){

    }

    /**
     * Stops client.
     */
    public void stopClient(){

    }
    @Override
    public void run(){

    }
}
