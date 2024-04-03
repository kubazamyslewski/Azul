package server;

import client.Client;

import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{

    private Session session;
    private int numberOfPlayers;
    private Socket serverSocket;
    private ArrayList<ClientManager> clients = new ArrayList<>();
    private int port;

    /**
     * Creates a server for given number of players.
     * @param numberOfPlayers number of players in the game
     * @param port port to start server on
     */
    public Server(int numberOfPlayers, int port){

    }

    /**
     * Removes ClientManager object from clients ArrayList
     */
    public void addClientManager(ClientManager clientManager){
        clients.add(clientManager);
    }
    public void removeClientManager(ClientManager clientManager){
        clients.remove(clientManager);
    }
    public Session getSession(){
        return session;
    }

    public void setSession(Session session){
        this.session = session;
    }
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public int getPort() {
        return port;
    }

    public Socket getServerSocket() {
        return serverSocket;
    }

    /**
     * Returns public IP address of server
     * @return String representing public IPv4 address of server
     */
    public String getPublicIP(){

    }

    /**
     * Stops server
     */
    public void stopServer(){

    }

    @Override
    public void run(){

    }

}
