package azul;

import Exceptions.ColorNotInTheMiddleException;
import Exceptions.WrongTileColourException;
import client.GameSession;
import server.Session;

/**
 * Handles a single player
 */
public class Player {

	private static int idGenerator = 1;

	private final GameSession game;
	private final Board playerBoard;
	private final int playerID;
	private int playerScore;

	public Player(GameSession game) {
		this.game = game;
		this.playerID = idGenerator++;
		this.playerScore = 0;
		this.playerBoard = new Board(this.game.getLinkedBox());
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
		if (this.playerScore<0){this.playerScore=0;}
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
	 */
	public void takeTile() throws ColorNotInTheMiddleException, WrongTileColourException {
		System.out.println("PLAYER: " + this.playerID);
		System.out.print("Choose tile pool (middle/workshop): ");
		String tilePool = this.game.getInputHandler().next();
		switch(tilePool) {
			case "middle":
				System.out.print("Choose color [1 - BLACK, 2 - WHITE, 3 - RED, 4 - YELLOW, 5 - BLUE]: ");
				int colorChoice = this.game.getInputHandler().nextInt();
				System.out.print("Choose row: ");
				int rowChoice = this.game.getInputHandler().nextInt();
				this.game.getLinkedTileDrawingPool().getMiddle().getTileColorFromMiddle(this, Tile.BLACK, rowChoice);
		}

	}
	
	
	/**
	 * Allows a player to Surrender
	 * @param session
	 */
	void surrender(Session session) {
		
	}
}
