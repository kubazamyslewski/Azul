package azul;

/**
 * Interface for classes that store tiles
 */
public interface Storage {
	
	/**
	 * Removes tiles from storage and adds it to a Bag for storing discarded tiles
	 * @param discardedBag
	 * @param color
	 * @param count
	 */
	public void remove(Bag discardedBag, Tile color, int count);
	
	/**
	 * Removes all tiles with a given color from storage and adds it to a Bag for storing discarded tiles
	 * @param discardedBag
	 * @param color
	 */
	public void removeAll(Bag discardedBag, Tile color);
	
	/**
	 * Removes all tiles with a given color from storage and adds it to a Bag for storing discarded tiles
	 * @param discardedBag
	 */
	public void removeAll(Bag discardedBag);
	
	/**
	 * Adds tiles with a given color to storage by drawing them from a bag
	 * @param bag
	 * @param color
	 * @param count
	 */
	public void add(Bag bag, Tile color, int count);
	
	/**
	 * Gets quantity of Tiles in a single Workshop/The Middle of The Table
	 * @param color
	 * @return
	 */
	public int getTileQuantity(Tile color);
	
	/**
	 * Checks whether a single Workshop/The Middle of The Table has tiles of a given color
	 * @param color
	 * @return
	 */
	public boolean hasColor(Tile color);
	
	
	/**
	 * Checks whether a single Workshop/The Middle of The Table is empty
	 * @return
	 */
	public boolean isEmpty();

}
