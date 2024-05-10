package main.client;

import main.Exceptions.FirstTileInWorkshopException;
import main.azul.Tile;
import main.server.NetworkGameSession;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientOne {
    private int id;
    private boolean isMyTurn = false;
    private boolean isReady = false;
    private Socket socket;
    private boolean isGameOver = false;
    private DataOutputStream out;
    private DataInputStream in; // Zmieniamy na DataInputStream
    private ObjectOutputStream objectSender;
    private ObjectInputStream objectReceiver;
    private Scanner scanner = new Scanner(System.in);;
    private NetworkGameSession networkGameState;


    public ClientOne() {
        try {
            socket = new Socket("127.0.0.1", main.server.Server.PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream()); // Zmieniamy na DataInputStream
            this.id = Integer.parseInt(in.readUTF());
            waitForStart();
            while(!isGameOver) {
                waitForCall();
                displayGameState();
                performMove();
            }
            //writeMessages();
        } catch (IOException | FirstTileInWorkshopException e) {
            e.printStackTrace();
        }
    }


    public void waitForStart() {
            try {
                out.writeUTF(scanner.nextLine());
                out.flush();
            } catch (IOException e) {
                System.err.println(e);
            }

    }

    public void reportReadiness() {
        System.out.println("Type in ready/exit:");
        String line = "";
        do {
            try {
                line = in.readUTF();
            } catch (IOException e) {
                System.err.println(e);
            }
        } while (!line.equals("exit") || !line.equals("ready"));
        if (line.equals("ready")) {
            this.isReady = true;
        } else if (line.equals("exit")) {
            try {
                close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } else {
            System.out.println("Something is very wrong.");
        }
    }

    public void waitForCall() {
        try {
            objectReceiver = new ObjectInputStream(socket.getInputStream());
            networkGameState = (NetworkGameSession) objectReceiver.readObject();
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
          throw new RuntimeException(e);
        }
    }

    public void displayGameState() throws FirstTileInWorkshopException {
        System.out.println("-------- GAME STATE --------");
        this.networkGameState.getLinkedTileDrawingPool().printState();
        this.networkGameState.getPlayers()[id].getBoard().printBoard();
        System.out.println("Your score: " + this.networkGameState.getPlayers()[id].getPlayerScore());
        System.out.println();
    }

    /*private void writeMessages() throws IOException {
        String line = "";
        while (!line.equals(main.server.Server.DISCONNECT_MESSAGE)) {
            line = scanner.nextLine();
            //System.out.println(line);
            out.writeUTF(line);
            out.flush(); // Upewniamy się, że dane są natychmiastowo wysyłane do serwera

            // Po wysłaniu żądania CAN_I_MOVE, klient oczekuje na odpowiedź od serwera
            if (line.equals("CAN_I_MOVE") || line.equals("NEXT")) {
                String message = in.readUTF();
                System.out.println(message);
            }
        }
        close();
    }*/

    public void performMove() {
        Move m = composeMove();
        sendMove(m);
    }


    //TODO polish in a way it is done in Player.takeTile() method
    public Move composeMove() {
        System.out.print("Choose (workshop/middle): ");
        String workshopOrMiddle = scanner.nextLine();
        int workshopNumber;
        switch(workshopOrMiddle) {
            case "workshop":
                System.out.println("Choose workshop number: ");
                workshopNumber = scanner.nextInt();
                break;
            case "middle":
                workshopNumber = -1;
                break;
            default:
                workshopNumber = -21;
                break;
        }
        System.out.print("Choose color: ");
        Tile color;
        int colorChoice = scanner.nextInt();
        switch(colorChoice) {
            case 1:
                color = Tile.BLACK;
                break;
            case 2:
                color = Tile.WHITE;
                break;
            case 3:
                color = Tile.RED;
                break;
            case 4:
                color = Tile.YELLOW;
                break;
            case 5:
                color = Tile.BLUE;
                break;
            default:
                color = null;
        }
        System.out.print("Choose row: ");
        int row = scanner.nextInt();
        Move m = new Move(workshopOrMiddle, workshopNumber, color, row, id);
        return m;
    }

    public void sendMove(Move m) {
        try {
            objectSender = new ObjectOutputStream(this.socket.getOutputStream());
            objectSender.writeObject(m);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void close() throws IOException {
        socket.close();
        out.close();
        in.close();
        scanner.close();
    }

    /**
     * Reads networkGame object sent from the Server
     */
    public void getStateFromServer() {

    }


    private boolean getIsMyTurn (){
        return isMyTurn;
    }

    private void setIsMyTurn (boolean whoseTurn){
        isMyTurn = whoseTurn;
    }

    public static void main(String[] args) {
        new ClientOne();
    }
}


