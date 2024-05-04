package client;

import azul.*;

import java.util.Scanner;

/**
 * Class responsible for setting up the game.
 */
public class GameSetup {
    private static int playerCount;
    private static TileDrawingPool tileDrawingPool;

    /**
     * Method to set up the game by initializing players, their boards, and the tile drawing pool.
     */
    public static void setupGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of players (2-4):");
        playerCount = scanner.nextInt();
        scanner.nextLine();
        Player[] players = new Player[playerCount];
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player(i + 1, new Board());
        }

        Bag bag = new Bag(20, 20, 20, 20, 20); // Initial number of tiles for each color

        tileDrawingPool = new TileDrawingPool(bag, playerCount);

        System.out.println("Game setup complete.");
        System.out.println("Player count: " + playerCount);

        tileDrawingPool.printState();
    }

    public static void main(String[] args) {
        setupGame();
    }
}
