package azul;

import Exceptions.FirstTileInWorkshopException;

/**
 * Interface for classes that store tiles
 */
public interface Storage {
	
	/**
	 * Gets quantity of Tiles in a single Workshop/The Middle of The Table
	 * @param color color of the tiles to be counted
	 * @return amount of the tiles of specified color
	 */
	int getTileQuantity(Tile color) throws FirstTileInWorkshopException;
	
	/**
	 * Checks whether a single Workshop/The Middle of The Table has tiles of a given color
	 * @param color color of the tile to be searched for
	 * @return boolean stating whether the storage contains a tile of specified color
	 */
	boolean hasColor(Tile color) throws FirstTileInWorkshopException;
	
	
	/**
	 * Checks whether a single Workshop/The Middle of The Table is empty
	 * @return boolean stating whether the storage is empty
	 */
	boolean isEmpty() throws FirstTileInWorkshopException;

}
