package azul;

import java.util.List;

/**
 * An area on a table, that all players can take tiles from
 */
public class TileDrawingPool {
	
	/**
	 * Initializes The Middle of The Table
	 */
	public Middle middle;
	private Workshop[] workshops;
	
	/**
	 * Return a table of current workshops
	 * @return
	 */
	public Workshop[] getWorkshops() {
		return workshops;
	}
	
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
	 * Checks whether there are absolutely no tiles players can take
	 * @return
	 */
	public boolean isEmpty() {
		return false;
		
	}
	
	public class Middle implements Storage {
		
		private List middleList;
		
		/**
		 * Transfers tiles from a workshop to The Middle of The Table
		 * @param workshop
		 */
		public void transferToTheMiddle(Workshop workshop) {
			
		}
		
		@Override
		public void removeAll(Box box, Tile color) {
			
		}
		
		@Override
		public void removeAll(Box box) {
			
		}

		@Override
		public void remove(Box box, Tile color, int count) {
			
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
