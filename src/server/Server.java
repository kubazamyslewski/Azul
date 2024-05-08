package server;
import azul.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
    private ArrayList<ClientManager> connectedClients;

    private final int port = 1234;
    private int playerOnTurn;
    private ServerSocket server;
    private int numberOfPlayers = 0;
    private TileDrawingPool currentDrawingPool;

    private Player player;        //tu mam rozkminę co zrobić bo to jest ten przekazywqany z klienta

    public static void main(String[] args) {
        Server server = new Server();
    }
    public Server(){
        connectedClients = new ArrayList<>();
        playerOnTurn = 1;
        try {
            server = new ServerSocket(port);
            System.out.println("Server started. Waiting for clients...");
            while(true){
                initConnection();
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initConnection() {
        Socket clientSocket;
        if (numberOfPlayers < 4){
            try {
                clientSocket = server.accept();
                if (clientSocket.isConnected()){
                    new Thread(() -> {
                        System.out.println("client " + numberOfPlayers + ": connected");
                        numberOfPlayers++;
                        ClientManager clientManager = new ClientManager(clientSocket, numberOfPlayers, this);
                        connectedClients.add(clientManager);
                        clientManager.readMessages();
                        clientManager.close();
                    }).start();
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void nextTurn(){
        int recentPlayer = playerOnTurn;
        if (playerOnTurn +1 > numberOfPlayers){
            playerOnTurn = 1;
        }
        else{
            playerOnTurn++;
        }
        for(ClientManager client: connectedClients){
            client.setTableReady(true);
            if(getPlayerOnTurn() == recentPlayer){
                client.setTableReady(false);
            }
        }
    }

    //TODO: get current table
    public synchronized void setCurrentTable(TileDrawingPool currentDrawingPool) {
        this.currentDrawingPool = currentDrawingPool;
    }

    public synchronized int getPlayerOnTurn(){
        return playerOnTurn;
    }
    /*
    public void addClientManager(ClientManager clientManager){
        connectedClients.add(clientManager);
    }
    public void removeClientManager(ClientManager clientManager){
        connectedClients.remove(clientManager);
    }
    public Session getSession(){
        return session;
    }*/

    /*public void setSession(Session session){
        this.session = session;
    }*/
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    public int getPort() {
        return port;
    }

    public String getPublicIP(){
        return "ADRES IP";
    }

    /**
     * stops server
     */

    public void stopServer(){


    }

    @Override
    public void run(){

    }

}
