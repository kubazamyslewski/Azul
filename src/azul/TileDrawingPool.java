package azul;

import java.util.List;

/**
 * An area on a table, that all players can take tiles from
 */
public class TileDrawingPool {
	
    private Tile[] availableColours=  new Tile[5];
	public Middle middle;
	private Workshop[] workshops;
    
	/**
     * Gets available colours.
     * @return - Array of tiles representing the available colours.
     */
    public Tile[] getAvailableColours() {
        return availableColours;
    }
	
	/**
	 * Return a table of current workshops
	 * @return
	 */
	public Workshop[] getWorkshops() {
		return workshops;
	}
	
	/**
	 * Creates new workshops
	 * @param playerCount
	 */
	public void initializeWorkshops(int playerCount) {
		
	}
	
	/**
	 * Fills all workshops with tiles from a bag
	 * @param bag
	 */
	public void fillWorkshops(Bag bag) {
		
	}
	
	/**
	 * Checks whether there are absolutely no tiles players can take
	 * @return
	 */
	public boolean isEmpty() {
		return false;
		
	}
}
