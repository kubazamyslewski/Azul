package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in; // Zmieniamy na DataInputStream
    private Scanner scanner;

    public Client() {
        try {
            socket = new Socket("127.0.0.1", server.Server.PORT);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream()); // Zmieniamy na DataInputStream
            scanner = new Scanner(System.in);
            writeMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeMessages() throws IOException {
        String line = "";
        while (!line.equals(server.Server.DISCONNECT_MESSAGE)) {
            line = scanner.nextLine();
            out.writeUTF(line);
            out.flush(); // Upewniamy się, że dane są natychmiastowo wysyłane do serwera

            if (line.equals("CAN_I_MOVE")) {
                boolean canMove = in.readBoolean(); // Odczytujemy boolean
                String message = in.readUTF(); // Odczytujemy String
                System.out.println("Can move: " + canMove);
                System.out.println("Message from server: " + message);
            }
        }
        close();
    }

    private void close() throws IOException {
        socket.close();
        out.close();
        in.close();
        scanner.close();
    }

    public static void main(String[] args) {
        new Client();
    }
}


