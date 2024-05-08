package server;
import azul.*;
import java.io.*;
import java.net.Socket;

/**
 * Class used to manage client's connection to a server.
 */
public class ClientManager implements Runnable {
    private Server server;
    private Socket clientSocket;
    private PrintWriter output;
    private boolean gameStarted = false;
    private ServerCommunication serverCommunication;
    private DataInputStream msgIn;
    private DataOutputStream msgOut;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private boolean isTableReady = false;
    private int id;

    public ClientManager(Socket clientSocket, int id, Server server) {
        this.server = server;
        this.clientSocket = clientSocket;
        this.id = id;
        try {
            System.out.println("Client " + id + ": connected");
            //this.clientSocket.setSendBufferSize(1024 * 100_000);
            //this.clientSocket.setReceiveBufferSize(1024 * 100_000);
            //this.clientSocket.setSoTimeout(10000);
            msgIn = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            msgOut = new DataOutputStream(clientSocket.getOutputStream());
            outStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void readMessages() {
        String line = "";
        boolean read = true;
        while (read) {
            try{
                line =msgIn.readUTF();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("client " + id + ": " + line);
            switch(line){
                //TODO: napisać komendy
                case "numberOfPlayers":

                    break;
                case "DISCONNECT":
                    read = false;
                    System.out.printf("Client %d: disconnected\n", id);
                    break;
                case "START":
                    startGame();
                    break;
                case "wrong":
                    wrong();
                    break;
                default:
                    sendMessage(line);
                    break;
            }
        }
    }

    private synchronized void startGame() {
        //TODO: napisać to do końca i przekazać dane do serwera
        System.out.println("Game started");
        try {
            TileDrawingPool tileDrawingPool = (TileDrawingPool) inStream.readObject();
            Player player = (Player) inStream.readObject();
            System.out.println("Map received from client");
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message){

    }
    private void sendNumberOfPlayers() {
        try {
            msgOut.writeInt(server.getNumberOfPlayers());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized void getPlayers() {
        int players = server.getNumberOfPlayers();
        try {
            msgOut.writeInt(players);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //TODO: disconnect
    public void disconnectClient(){

    }

    /**
     * Informs client about starting turn.
     */
    /*public void startTurn(){

    }*/
    public void setTableReady(boolean tableReady) {isTableReady = tableReady;}



    /**
     * Informs client about end of turn.
     */
    /*
    public void endTurn(){

    }
    public void ok(){

    }*/

    /**
     * Informs client about rejected move.
     */
    public void wrong(){

    }
    public synchronized void close() {
        try {
            msgIn.close();
            msgOut.close();
            inStream.close();
            outStream.close();
            clientSocket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*
    public synchronized boolean isGameStarted() {
        return gameStarted;
    }

    public synchronized void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }*/
    @Override
    public void run(){

    }
}

