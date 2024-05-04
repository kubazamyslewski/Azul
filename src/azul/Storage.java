package azul;

import Exceptions.FirstTileInWorkshopException;

/**
 * Interface for classes that store tiles
 */
public interface Storage {
	/**
	 * Removes tiles from storage and adds it to the Box for storing discarded tiles
	 * @param discardedBag
	 * @param color
	 * @param count
	 */
	//public void remove(Box box, Tile color, int count);
	
	/**
	 * Removes all tiles with a given color from storage and adds it to the Box for storing discarded tiles
	 * @param discardedBag
	 * @param color
	 */
	//public void removeAll(Box box, Tile color);
	
	/**
	 * Removes all tiles with a given color from storage and adds it to the Box for storing discarded tiles
	 * @param discardedBag
	 */
	//public void removeAll(Box box);
	//te metody chyba nie są potrzebne? przynajmniej nie w każdej klasie
	
	/**
	 * Gets quantity of Tiles in a single Workshop/The Middle of The Table
	 * @param color
	 * @return
	 */
	public int getTileQuantity(Tile color) throws FirstTileInWorkshopException;
	
	/**
	 * Checks whether a single Workshop/The Middle of The Table has tiles of a given color
	 * @param color
	 * @return
	 */
	public boolean hasColor(Tile color) throws FirstTileInWorkshopException;
	
	
	/**
	 * Checks whether a single Workshop/The Middle of The Table is empty
	 * @return
	 */
	public boolean isEmpty();

}
