package server;

import java.io.BufferedReader;
import java.net.Socket;

public class ServerCommunication implements Runnable{
    private BufferedReader input;
    ClientManager clientManager;
    public ServerCommunication(Socket socket, ClientManager clientManager){

    }

    /**
     * Describes server's actions after receiving certain messages
     * @param message message received from a client
     */
    public void handleMessage(String message){

    }
    @Override
    public void run(){

    }
}
