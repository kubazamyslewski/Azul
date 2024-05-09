package main.server;

import java.io.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import main.azul.Player;
import main.azul.TileDrawingPool;

public class ConnectedClient {

    private  DataOutputStream out;
    private Socket clientSocket;
    private DataInputStream in;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private boolean isTableReady = false;
    private TileDrawingPool currentTileDrawingPool;
    private Player player;
    private boolean isMyTurn;


    private int id;
    private Server server;

    public ConnectedClient(Socket clientSocket, int id, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.id = id;

        try {
            System.out.println("Client "+id+ ": Client Connected");
            in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessages(){
        String line = "";
        boolean read = true;
        while (read){
            try{
                line = in.readUTF();
                //System.out.println(line);
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
            switch (line){
                case "DISCONNECT":
                    read = false;
                    System.out.println("Player " + id + " Disconnected");
                    break;
                case "START":
                    startGame();
                    System.out.println("Game is starting!!!");
                    break;
                case "CAN_I_MOVE":
                    sendIfCanMove();
                    break;
                case "NEXT":
                    //System.out.println("id " + id + "server.getPlayerOnTurn " + server.getPlayerOnTurn());
                    if (id == server.getPlayerOnTurn()){
                        sendNextTurn();
                        server.nextPlayer();
                        //System.out.println("spoko");
                        break;
                    }
                    else{
                        System.out.println("spierdalaj");
                        sendNextTurn();
                        break;
                    }
                default:
                    throw new IllegalStateException("Unexpected value: " + line);
            }
        }
    }

    private void startGame() {
        System.out.println("Game started!");
        if (id == server.getPlayerOnTurn()){
            isMyTurn = true;
        } else {
            isMyTurn = false;
        }
        try{
            TileDrawingPool tileDrawingPool =(TileDrawingPool)inStream.readObject();
            server.setCurrentPool(tileDrawingPool);
            Player player = (Player)inStream.readObject();
            setCurrentPlayer(player);
        }
        catch (Exception e){
            System.out.println("Nie można wczytać obiektów");
        }

    }

    public synchronized void setCurrentPlayer(Player player) {
        this.player = player;
    }

    private void sendIfCanMove() {
        boolean canMove = server.getPlayerOnTurn() == id;
        if (canMove) {
            sendMessage("Możesz się ruszyć.");
        } else {
            sendMessage("Nie możesz się ruszyć.");
        }
    }

    private void sendNextTurn (){
        boolean canMove = server.getPlayerOnTurn() == id;
        if (canMove) {
            sendMessage("Nastąpiła zmiana gracza");
        } else {
            sendMessage("Nie możesz się ruszyć.");
        }
    }

    private void sendMessage(String message) {
        try {
            out.writeUTF(message);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close(){
        try{
            clientSocket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPlayerNumber() {
        return id;
    }
}