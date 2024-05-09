package main.server;
import main.azul.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket server;
    public static final int PORT = 3030;
    private TileDrawingPool currentTileDrawingPool;
    public static final String DISCONNECT_MESSAGE = "DISCONNECT";
    private List<ConnectedClient> connectedClients;
    private int playerOnTurn;
    private int numberOfPlayers=0;
    private int index = 0;

    public Server(){
        connectedClients = new ArrayList<>();
        playerOnTurn = 1;
        try{
            server = new ServerSocket(PORT);
            while(true) {
                initConnections();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initConnections() throws IOException {
        numberOfPlayers =1;
        if (numberOfPlayers<4){
            Socket clientSocket = server.accept();
            if(clientSocket.isConnected())
                new Thread(()->{
                    numberOfPlayers ++;
                    index++;
                    ConnectedClient client = new ConnectedClient(clientSocket, index, this);
                    connectedClients.add(client);
                    client.readMessages();
                    client.close();
                }).start();
        }
    }

    public void startGame (){

    }

    public void nextTurn (){

    }
    public int getPlayerOnTurn(){
        return playerOnTurn;
    }

    public synchronized void setCurrentPool(TileDrawingPool currentTileDrawingPool) {
        this.currentTileDrawingPool = currentTileDrawingPool;
    }


    public static void main(String[] args) {
        new Server();
    }
}
