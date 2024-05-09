package main.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import main.server.NetworkGameSession;
import main.client.Move;
import main.azul.Tile;

public class Client {
    private boolean isMyTurn = false;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in; // Zmieniamy na DataInputStream
    private Scanner scanner = new Scanner(System.in);;
    private NetworkGameSession networkGame;


    public Client() {
        try {
            socket = new Socket("127.0.0.1", main.server.Server.PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream()); // Zmieniamy na DataInputStream
            writeMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeMessages() throws IOException {
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
    }

    public void composeMove() {
        System.out.println("Choose (workshop/middle):");
        String workshopOrMiddle = scanner.nextLine();
        System.out.println("Choose color:");
        Tile color;
        int colorChoice = scanner.nextInt();
    }

    private void close() throws IOException {
        socket.close();
        out.close();
        in.close();
        scanner.close();
    }
    private boolean getIsMyTurn (){
        return isMyTurn;
    }
    private void setIsMyTurn (boolean whoseTurn){
        isMyTurn = whoseTurn;
    }
    public static void main(String[] args) {
        new Client();
    }
}


