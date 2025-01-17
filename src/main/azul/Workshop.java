package main.azul;

import main.Exceptions.ColorNotInWorkshopException;
import main.Exceptions.FirstTileInWorkshopException;
import main.Exceptions.WrongTileColourException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Single Workshop
 */
public class Workshop implements Storage, Serializable {

	private final Map<Tile, Stack<Tile>> workshopMap;

	private final TileDrawingPool parentTileDrawingPool;

	/**
	 * Constructor for a workshop, initializing it with four random tiles
	 * from a bag
	 */
	public Workshop(TileDrawingPool parentTileDrawingPool) {
		this.parentTileDrawingPool = parentTileDrawingPool;
		this.workshopMap = new HashMap<>();
		for(Tile t : Tile.values()) {
			workshopMap.put(t, new Stack<>());
		}
		for (int i = 0; i < 4; i++) {
			this.addTile();
		}
	}

	/**
	 * Adds a tile from a bag to the workshop
	 */
	public void addTile() {
		if(!this.parentTileDrawingPool.getLinkedBag().isEmpty()) {
			Tile tileToAdd = parentTileDrawingPool.getLinkedBag().pop();
			this.workshopMap.get(tileToAdd).push(tileToAdd);
		}
	}

	public void refill() throws FirstTileInWorkshopException {
		if (this.isEmpty()) {
			for(int i = 0; i < 4; i++) {
				if(this.parentTileDrawingPool.getLinkedBag().isEmpty()) this.parentTileDrawingPool.getLinkedBag().refillTheBagFromTheBox();
				this.addTile();
			}
		}
		else System.out.println("Workshop not empty between rounds somehow..........");
	}
    
	@Override
	public boolean hasColor(Tile color) throws FirstTileInWorkshopException{
    	if (!this.workshopMap.get(Tile.FIRST).isEmpty()) throw new FirstTileInWorkshopException("First tile cannot be present in a workshop");
		return !workshopMap.get(color).isEmpty();
	}

	@Override
	public int getTileQuantity(Tile color) throws FirstTileInWorkshopException{
		if (!this.workshopMap.get(Tile.FIRST).isEmpty()) throw new FirstTileInWorkshopException("First tile cannot be present in a workshop");
		return this.workshopMap.get(color).size();
	}
	
	@Override
	public boolean isEmpty() throws FirstTileInWorkshopException {
    return this.getTileQuantity(Tile.BLACK) + this.getTileQuantity(Tile.WHITE) +
						this.getTileQuantity(Tile.RED) + this.getTileQuantity(Tile.YELLOW) +
						this.getTileQuantity(Tile.BLUE) == 0;
	}
	
	/**
	 * Checks whether a workshop is full
	 * @return boolean stating whether a workshop is full
	 */
	public boolean isFull() throws FirstTileInWorkshopException {
		return this.getTileQuantity(Tile.BLACK) + this.getTileQuantity(Tile.WHITE) +
						this.getTileQuantity(Tile.RED) + this.getTileQuantity(Tile.YELLOW) +
						this.getTileQuantity(Tile.BLUE) == 4;
	}

	/**
	 * Takes certain color of tiles from a workshop. Then, transports them to the player's wall.
	 * Tiles of other colors are transported to the middle.
	 * @param player player who's taking the tiles
	 * @param color color of tiles to be transferred to the player
	 * @param row row of the wall to add the tiles to
	 * @throws ColorNotInWorkshopException
	 * @throws FirstTileInWorkshopException
	 */
	public void getTileColorFromWorkshop(Player player, Tile color, int row) throws ColorNotInWorkshopException, FirstTileInWorkshopException, WrongTileColourException {

		if(hasColor(color)) {

			while(this.getTileQuantity(color) != 0) {
				player.getBoard().getWall().addToTempStorage(this.workshopMap.get(color).pop());
			}

			// transfer kafelków z tempStorage na Wall
			player.getBoard().getWall().addTilesToWall(row);

			// pozostałe kafelki do Middle
			for(Tile tileColor : Tile.values()) {
				while(!this.workshopMap.get(tileColor).isEmpty()) {
					this.parentTileDrawingPool.getMiddle().add(this.workshopMap.get(tileColor).pop());
				}
			}

		}
		else throw new ColorNotInWorkshopException("There is no such color in this workshop!");

	}
}
