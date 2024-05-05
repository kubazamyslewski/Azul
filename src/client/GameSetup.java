package client;

import Exceptions.FirstTileInWorkshopException;
import azul.*;
import java.util.Scanner;


public class GameSetup {
    private static int playerCount;
    private static TileDrawingPool tileDrawingPool;


    public static void setupGame() throws FirstTileInWorkshopException {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter the amount of players (2-4): ");
            playerCount = scanner.nextInt();
            if (playerCount < 2 || playerCount > 4) {
                System.out.println("Incorrect number of players!");
            }
        } while (playerCount < 2 || playerCount > 4);
        Box gameBox = new Box();
        System.out.println();
        scanner.nextLine();
        Player[] players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player(i + 1, new Board(gameBox));
        }

        Bag bag = new Bag(gameBox, 20, 20, 20, 20, 20); // Initial number of tiles for each color

        tileDrawingPool = new TileDrawingPool(bag, playerCount);

        System.out.println("Game setup complete.");
        System.out.println("Player count: " + playerCount);

        tileDrawingPool.printState();
    }

    public static void main(String[] args) throws FirstTileInWorkshopException { setupGame(); }
}
