package main.azul;

import main.Exceptions.FirstTileInWorkshopException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An area on a table, that all players can take tiles from
 */
public class TileDrawingPool implements Serializable {
	
	private final List<Tile> availableColors = new ArrayList<>();
	private final Middle middle;
	private final Bag linkedBag;
	private Workshop[] workshops;

	public TileDrawingPool(Bag bag, int playerCount) {
		this.middle = new Middle(this);
		this.linkedBag = bag;
		initializeWorkshops(playerCount, linkedBag);
	}

	/**
	 * Creates new workshops based on the number of players
	 * @param playerCount number of players
	 */
	public void initializeWorkshops(int playerCount, Bag bag) {
		int workshopCount = switch (playerCount) {
			case 2 -> 5;
			case 3 -> 7;
			case 4 -> 9;
			default -> throw new IllegalArgumentException("Invalid number of players");
		};
		workshops = new Workshop[workshopCount];
		for (int i = 0; i < workshopCount; i++) {
			workshops[i] = new Workshop(this);
		}
	}
    
	/**
	 * Gets available colours.
	 * @return - List of tiles representing the available colours.
	 */
    public List<Tile> getAvailableColours() {
        return availableColors;
    }
	
	/**
	 * Returns a table of current workshops
     */
	public Workshop[] getWorkshops() { return this.workshops; }

	public Middle getMiddle() { return this.middle; }

	public Bag getLinkedBag() { return this.linkedBag; }
	
	/**
	 * Checks whether there are absolutely no tiles players can take
	 * @return true if all workshops and middle is empty, false otherwise
	 */
	public boolean isEmpty() throws FirstTileInWorkshopException {
		boolean isEmpty = availableColors.isEmpty();
		for (Workshop workshop : workshops) {
			isEmpty &= workshop.isEmpty();
		}
		return middle.isEmpty() && isEmpty;
	}
	public void printState() throws FirstTileInWorkshopException {

		String GREEN = "\u001B[32m";

		System.out.println();
		System.out.println(GREEN + "Workshops:");
		for (int i = 0; i < workshops.length; i++) {
			Workshop workshop = workshops[i];
			System.out.print("Workshop " + (i + 1) + ": ");
			printTileQuantity(workshop.getTileQuantity(Tile.BLACK),
								workshop.getTileQuantity(Tile.WHITE),
								workshop.getTileQuantity(Tile.RED),
								workshop.getTileQuantity(Tile.YELLOW),
								workshop.getTileQuantity(Tile.BLUE));
		}
		System.out.println();
		System.out.println(GREEN + "Middle:");
		printTileQuantity(middle.getTileQuantity(Tile.BLACK),
							middle.getTileQuantity(Tile.WHITE),
							middle.getTileQuantity(Tile.RED),
							middle.getTileQuantity(Tile.YELLOW),
							middle.getTileQuantity(Tile.BLUE));

		System.out.println();
	}

	public static void printTileQuantity(int tileQuantity, int tileQuantity2, int tileQuantity3, int tileQuantity4, int tileQuantity5) {
		System.out.print("Black: " + tileQuantity + ", ");
		System.out.print("White: " + tileQuantity2 + ", ");
		System.out.print("Red: " + tileQuantity3 + ", ");
		System.out.print("Yellow: " + tileQuantity4 + ", ");
		System.out.println("Blue: " + tileQuantity5);
	}
}
