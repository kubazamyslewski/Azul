package azul;

import server.Session;

/**
 * Handles scores of all players
 */
public class Score {
	
	private Player[] players;
	
	/**
	 * Resets player's score
	 * @param player
	 */
	public void reset(Player player) {
		
	}
	
	/**
	 * Gets the current winner
	 * @param session
	 * @return
	 */
	public Player getCurrentWinner(Session session) {
		return new Player();
	}
	
	/**
	 * Rewards a player with points when they place a new tile
	 * @param player
	 */
	public void scoreNewTile(Player player) {
		
	}
	
	
	/**
	 * Rewards a player with points for a full row
	 * @param player
	 * @param row
	 */
	public void scoreRow(Player player, int row) {
		
	}
	
	/**
	 * Rewards a player with points for a full column
	 * @param player
	 * @param column
	 */
	public void scoreColumn(Player player, int column) {
		
	}
	
	/**
	 * Rewards a player with points for a full color
	 * @param player
	 */
	public void fullColor(Player player) {
		
	}
	
	/**
	 * Takes points from players for tiles on the floor 
	 * @param player
	 */
	public void floorPenalty(Player player) {
		
	}
}
