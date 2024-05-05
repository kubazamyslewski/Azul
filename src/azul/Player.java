package azul;

import server.Session;

/**
 * Handles a single player
 */
public class Player {
	
	private final Board playerBoard;
	private final int playerID;

	public Player(int playerID, Board board) {
		this.playerID = playerID;
		this.playerBoard = board;
	}

	public Board getBoard() { return this.playerBoard; }
	
	/**
	 * Checks whether a player has the tile with number 1 on it
	 * @return
	 */
	public boolean isStartingPlayer() {
		return false;
		
	}
	
	/**
	 * Assigns Tile 1 to a player and removes it from another player
	 * @param player Player from which the tile is removed
	 */
	void changeStartingPlayer(Player player) {
		
	}
	
	
	/**
	 * Assigns Tile 1 to a player
	 */
	void setStartingPlayer() {
		
	}
	
	/**
	 * Allows a player to take a tile from a Workshop or The Middle of The Table
	 * @param storage
	 */
	void takeTile(Storage storage) {
		
	}
	
	
	/**
	 * Allows a player to Surrender
	 * @param session
	 */
	void surrender(Session session) {
		
	}
}
