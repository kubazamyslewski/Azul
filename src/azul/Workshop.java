package azul;

import Exceptions.ColorNotInWorkshopException;
import Exceptions.FirstTileInWorkshopException;
import Exceptions.WrongTileColourException;

import java.util.Stack;

/**
 * Single Workshop
 */
public class Workshop implements Storage {

	private final Stack<Tile> blackTiles = new Stack<>();
	private final Stack<Tile> whiteTiles = new Stack<>();
	private final Stack<Tile> redTiles = new Stack<>();
	private final Stack<Tile> yellowTiles = new Stack<>();
	private final Stack<Tile> blueTiles = new Stack<>();

	/**
	 * Constructor for a workshop, initializing it with four random tiles
	 * from a bag
	 */
	public Workshop(Bag bag) {
		for (int i = 0; i < 4; i++) {
			this.add(bag);
		}
	}

	public void add(Bag bag) {
		Tile tileToAdd = bag.pop();
		switch (tileToAdd) {
			case BLACK:
				this.blackTiles.add(tileToAdd);
				break;
			case WHITE:
				this.whiteTiles.add(tileToAdd);
				break;
			case RED:
				this.redTiles.add(tileToAdd);
				break;
			case YELLOW:
				this.yellowTiles.add(tileToAdd);
				break;
			case BLUE:
				this.blueTiles.add(tileToAdd);
				break;
		}
	}
    
	@Override
	public boolean hasColor(Tile colour) throws FirstTileInWorkshopException{
    return switch (colour) {
			case FIRST -> throw new FirstTileInWorkshopException("First tile cannot be in a workshop!");
      case BLACK -> blackTiles.isEmpty();
      case WHITE -> whiteTiles.isEmpty();
      case RED -> redTiles.isEmpty();
      case YELLOW -> yellowTiles.isEmpty();
      case BLUE -> blueTiles.isEmpty();
		};
	}

	@Override
	public int getTileQuantity(Tile colour) throws FirstTileInWorkshopException{
    return switch (colour) {
			case FIRST -> throw new FirstTileInWorkshopException("First tile cannot be in a workshop!");
      case BLACK -> blackTiles.size();
      case WHITE -> whiteTiles.size();
      case RED -> redTiles.size();
      case YELLOW -> yellowTiles.size();
      case BLUE -> blueTiles.size();
		};
	}
	
	@Override
	public boolean isEmpty() {
    return blackTiles.isEmpty() && whiteTiles.isEmpty()
            && redTiles.isEmpty() && yellowTiles.isEmpty()
            && blueTiles.isEmpty();
	}
	
	/**
	 * Checks whether a workshop is full
	 * @return boolean stating whether a workshop is full
	 */
	public boolean isFull() {
		return blackTiles.size() + whiteTiles.size()
						+ redTiles.size() + yellowTiles.size()
						+ blueTiles.size() == 4;
	}

	/**
	 * Takes certain color of tiles from a workshop. Then, transports them to the player's wall.
	 * Tiles of other colors are transported to the middle.
	 * @param color color of tiles to be transferred to the player
	 * @param mid middle to add discarded tiles from a workshop to
	 * @param wall wall to add tiles from a workshop to
	 * @param row row of the wall to add the tiles to
	 * @throws ColorNotInWorkshopException
	 * @throws FirstTileInWorkshopException
	 */
	public void getTileColorFromWorkshop(Tile color, int row, Middle mid, Wall wall) throws ColorNotInWorkshopException, FirstTileInWorkshopException, WrongTileColourException {
		if(hasColor(color)) {
			switch(color) {
				case BLACK: while(getTileQuantity(Tile.BLACK) != 0) {
					Tile removedTile = this.blackTiles.pop();
					wall.addToTempStorage(removedTile);
				} break;
				case WHITE: while(getTileQuantity(Tile.WHITE) != 0) {
					Tile removedTile = this.whiteTiles.pop();
					wall.addToTempStorage(removedTile);
				} break;
				case RED: while(getTileQuantity(Tile.RED) != 0) {
					Tile removedTile = this.redTiles.pop();
					wall.addToTempStorage(removedTile);
				} break;
				case YELLOW: while(getTileQuantity(Tile.YELLOW) != 0) {
					Tile removedTile = this.yellowTiles.pop();
					wall.addToTempStorage(removedTile);
				} break;
				case BLUE: while(getTileQuantity(Tile.BLUE) != 0) {
					Tile removedTile = this.blueTiles.pop();
					wall.addToTempStorage(removedTile);
				} break;
			}
			// transfer kafelków z tempStorage na Wall
			wall.addTilesToWall(row, wall.linkedMosaic);
			// pozostałe kafelki do Middle
			for(Tile tileColor : Tile.values()){
					switch(color) {
						case BLACK: while(getTileQuantity(Tile.BLACK) != 0) {
							mid.add(this.blackTiles.pop());
						} break;
						case WHITE: while(getTileQuantity(Tile.WHITE) != 0) {
							mid.add(this.whiteTiles.pop());
						} break;
						case RED: while(getTileQuantity(Tile.RED) != 0) {
							mid.add(this.redTiles.pop());
						} break;
						case YELLOW: while(getTileQuantity(Tile.YELLOW) != 0) {
							mid.add(this.yellowTiles.pop());
						} break;
						case BLUE: while(getTileQuantity(Tile.BLUE) != 0) {
							mid.add(this.blueTiles.pop());
						} break;
					}
			}
		}
		else throw new ColorNotInWorkshopException("There is no such color in this workshop!");
	}
}
