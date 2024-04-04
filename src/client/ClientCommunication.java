package client;

import java.io.BufferedReader;
import java.net.Socket;

/**
 * Class used to send and receive messages to and from a server.
 */
public class ClientCommunication implements Runnable{
    Client client;
    private BufferedReader input;
    public ClientCommunication(Client client, Socket socket){

    }

    /**
     * Describes client's actions after receiving certain messages
     * @param message message received from a server.
     */
    public void handleMessage(String message){

    }

    @Override
    public void run(){

    }


}
