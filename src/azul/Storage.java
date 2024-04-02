package azul;

public interface Storage {
	
	public void remove(Bag bag, Tile color, int count);
	
	public void removeAll(Bag discarded, Tile color);
	
	public void removeAll(Bag discarded);
	
	public void add(Bag bag, Tile color, int count);
	
	public void getTileQuantity(Tile color);
	
	public boolean hasColor(Tile color);
	
	public boolean isEmpty();

}
