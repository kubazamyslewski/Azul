package azul;

/**
 * Single Workshop
 */
public class Workshop implements Storage {
	
	/**
	 * Constructor for a workshop
	 */
	public Workshop(Bag bag) {
		
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
	public void add(Bag bag, Tile color, int count) {
		
	}
    
	@Override
	public boolean hasColor(Tile color) {
		return false;
	}

	@Override
	public int getTileQuantity(Tile color) {
		return 0;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
		
	}
	
	/**
	 * Checks whether a workshop is full
	 * @return
	 */
	public boolean isFull() {
		return false;
		
	}
}
