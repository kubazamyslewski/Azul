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
}
