package server;

import java.io.*;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectedClient {

    private  DataOutputStream out;
    private Socket clientSocket;
    private DataInputStream in;
    private boolean isTableReady = false;

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
                    System.out.println("Game is starting!!!");
                    break;
                case "CAN_I_MOVE":
                    sendIfCanMove();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + line);
            }
        }
    }
    private void sendIfCanMove() {
        try {
            boolean canMove = server.getPlayerOnTurn() == id;
            out.writeBoolean(canMove);
            if (canMove) {
                out.writeUTF("Możesz się ruszyć.");
            } else {
                out.writeUTF("Nie możesz się ruszyć.");
            }
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
}