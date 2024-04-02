package azul;

/**
 * An area on a table, that all players can take tiles from
 */
public class TileDrawingPool {
	
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
	 * Initializes The Middle of The Table
	 */
	public void initializeMiddle(Bag bag) {
		
	}
	
	/**
	 * Checks whether there are absolutely no tiles players can take
	 * @return
	 */
	public boolean isEmpty() {
		return false;
		
	}
	
	public class Middle implements Storage {
		
		/**
		 * Transfers tiles from a workshop to The Middle of The Table
		 * @param workshop
		 */
		public void transferToTheMiddle(Workshop workshop) {
			
		}
		
		@Override
		public void removeAll(Bag discarded, Tile color) {
			
		}
		
		@Override
		public void removeAll(Bag discarded) {
			
		}

		@Override
		public void remove(Bag bag, Tile color, int count) {
			
		}

		@Override
		public void add(Bag bag, Tile color, int count) {
			
		}

		@Override
		public int getTileQuantity(Tile color) {
			return 0;
		}

		@Override
		public boolean hasColor(Tile color) {
			return false;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}
		
	}

}
