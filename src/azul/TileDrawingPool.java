package azul;

import java.util.ArrayList;
import java.util.List;

/**
 * An area on a table, that all players can take tiles from
 */
public class TileDrawingPool {
	
    private List<Tile> availableColors = new ArrayList<>();
	public Middle middle;
	private Workshop[] workshops;

	public TileDrawingPool(Bag bag, int playerCount) {
		middle = new Middle();
		initializeWorkshops(playerCount, bag);
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
	public Workshop[] getWorkshops() {
		return workshops;
	}

	public Middle getMiddle() {
		return middle;
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
			workshops[i] = new Workshop(bag);
		}
	}
	
	/**
	 * Checks whether there are absolutely no tiles players can take
	 * @return true if all workshops and middle is empty, false otherwise
	 */
	public boolean isEmpty() {
		boolean isEmpty = availableColors.isEmpty();
		for (Workshop workshop : workshops) {
			isEmpty &= workshop.isEmpty();
		}
		return middle.isEmpty() && isEmpty;
	}
	public void printState() {
		System.out.println();
		System.out.println("Workshops:");
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
		System.out.println("Middle:");
		printTileQuantity(middle.getTileQuantity(Tile.BLACK),
							middle.getTileQuantity(Tile.WHITE),
							middle.getTileQuantity(Tile.RED),
							middle.getTileQuantity(Tile.YELLOW),
							middle.getTileQuantity(Tile.BLUE));
	}

	public static void printTileQuantity(int tileQuantity, int tileQuantity2, int tileQuantity3, int tileQuantity4, int tileQuantity5) {
		System.out.print("Black: " + tileQuantity + ", ");
		System.out.print("White: " + tileQuantity2 + ", ");
		System.out.print("Red: " + tileQuantity3 + ", ");
		System.out.print("Yellow: " + tileQuantity4 + ", ");
		System.out.println("Blue: " + tileQuantity5);
	}
}
