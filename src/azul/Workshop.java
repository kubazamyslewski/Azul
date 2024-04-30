package azul;

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
	
	@Override
    public void remove(Box box, Tile color, int count) {

	}
	
    @Override
	public void removeAll(Box box, Tile color) {
		
	}
	
	@Override
	public void removeAll(Box box) {
		
	}
	
	@Override
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
	public boolean hasColor(Tile colour) {
    return switch (colour) {
      case BLACK -> blackTiles.isEmpty();
      case WHITE -> whiteTiles.isEmpty();
      case RED -> redTiles.isEmpty();
      case YELLOW -> yellowTiles.isEmpty();
      case BLUE -> blueTiles.isEmpty();
		};
	}

	@Override
	public int getTileQuantity(Tile colour) {
    return switch (colour) {
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
}
