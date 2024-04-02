package azul;

/**
 * Single Workshop
 */
public class Workshop implements Storage {
	
	public Workshop(Bag bag) {
		
	}
	
	@Override
    public void remove(Bag discarded, Tile color, int count) {
		
	}
	
    @Override
	public void removeAll(Bag discarded, Tile color) {
		
	}
	
    @Override
	public void removeAll(Bag discarded) {
		
	}
	
    @Override
	public void add(Bag bag, Tile color, int count) {
		
	}
    
	@Override
	public boolean hasColor(Tile color) {
		return false;
	}

	@Override
	public void getTileQuantity(Tile color) {
		
	}
	
	@Override
	public boolean isEmpty() {
		return false;
		
	}
	
	public boolean isFull() {
		return false;
		
	}
	
	
	
	
}
