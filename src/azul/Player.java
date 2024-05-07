package azul;

import Exceptions.ColorNotInTheMiddleException;
import Exceptions.ColorNotInWorkshopException;
import Exceptions.FirstTileInWorkshopException;
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
	public int getPlayerID() {
		return this.playerID;
	}
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
	//TODO: as of right now picking a tile from middle results in exception being thrown
	public void takeTile() throws ColorNotInTheMiddleException, WrongTileColourException, FirstTileInWorkshopException, ColorNotInWorkshopException {
		System.out.println("PLAYER: " + this.playerID);
		this.game.getLinkedTileDrawingPool().printState();

		String tilePool;
		int rowChoice;
		Tile chosenColor = null;
		Score score = new Score(this.game.getPlayers());

		do {
			// checks whether middle is empty, if so go to picking workshop
			if (!this.game.getLinkedTileDrawingPool().getMiddle().colorsEmpty()) {
				System.out.print("Choose tile pool (middle/workshop): ");
				tilePool = this.game.getInputHandler().next();
			} else {
				tilePool = "workshop";
			}

			switch (tilePool) {
				case "workshop":
					Workshop chosenWorkshop = null;
					do {
						chosenWorkshop = this.chooseWorkshop();
					} while (chosenWorkshop.isEmpty());

					do {
						chosenColor = this.chooseColorFromWorkshop(chosenWorkshop);
					} while (chosenColor == null);

					rowChoice = this.chooseRow(chosenColor);

					chosenWorkshop.getTileColorFromWorkshop(this.game.getPlayers()[this.game.getIndexFromPlayerID(this.playerID)], chosenColor, rowChoice);
					score.scoreNewTile(this.game.getPlayers()[this.game.getIndexFromPlayerID(this.playerID)], chosenColor, rowChoice);
					break;

				case "middle":
					do {
						chosenColor = this.chooseColorFromMiddle();
					} while (chosenColor == null);

					rowChoice = this.chooseRow(chosenColor);

					this.game.getLinkedTileDrawingPool().getMiddle().getTileColorFromMiddle(this.game.getPlayers()[this.game.getIndexFromPlayerID(this.playerID)], chosenColor, rowChoice);
					score.scoreNewTile(this.game.getPlayers()[this.game.getIndexFromPlayerID(this.playerID)], chosenColor, rowChoice);
					break;

				default:
					System.out.println("Invalid input.");
					break;
			}

			System.out.println("Player " + playerID + " points: " + getPlayerScore());
			System.out.println();
		} while (!(tilePool.equals("workshop") || tilePool.equals("middle")));
	}

	private Workshop chooseWorkshop() throws FirstTileInWorkshopException {
		int workshopChoice;
		Workshop chosenWorkshop = null;
		do {
			System.out.print("Choose workshop (1 - " + (2 * game.getPlayerCount() + 1) + "): ");
			workshopChoice = this.game.getInputHandler().nextInt();
			if (workshopChoice < 1 || workshopChoice > (2 * game.getPlayerCount() + 1)) {
				System.out.println("This workshop does not exist!");
				continue;
			}
			chosenWorkshop = this.game.getLinkedTileDrawingPool().getWorkshops()[workshopChoice - 1];
			if (chosenWorkshop.isEmpty()) {
				System.out.println("This workshop is empty!");
			}
		} while (chosenWorkshop == null || chosenWorkshop.isEmpty());

		return chosenWorkshop;
	}
	private Tile chooseColorFromWorkshop(Workshop workshop) throws FirstTileInWorkshopException {
		int colorChoice;
		Tile chosenColor = null;
		do {
			System.out.print("Choose color [1 - BLACK, 2 - WHITE, 3 - RED, 4 - YELLOW, 5 - BLUE]: ");
			colorChoice = this.game.getInputHandler().nextInt();
			if (colorChoice < 1 || colorChoice > 5) {
				System.out.println("Incorrect color!");
				continue;
			}
			chosenColor = Tile.values()[colorChoice];
			if (!workshop.hasColor(chosenColor)) {
				System.out.println("Picked a color which isn't available, try again!");
				chosenColor = null;
			}
		} while (chosenColor == null);

		return chosenColor;
	}

	private Tile chooseColorFromMiddle() {
		int colorChoice;
		Tile chosenColor = null;
		do {
			System.out.print("Choose color [1 - BLACK, 2 - WHITE, 3 - RED, 4 - YELLOW, 5 - BLUE]: ");
			colorChoice = this.game.getInputHandler().nextInt();
			if (colorChoice < 1 || colorChoice > 5) {
				System.out.println("Incorrect color!");
				continue;
			}
			chosenColor = Tile.values()[colorChoice];
			if (!this.game.getLinkedTileDrawingPool().getMiddle().hasColor(chosenColor)) {
				System.out.println("Picked a color which isn't available, try again!");
				chosenColor = null;
			}
		} while (chosenColor == null);

		return chosenColor;
	}
	private int chooseRow(Tile chosenColor) {
		int rowChoice;
		boolean isRowCorrect;
		do {
			isRowCorrect = true;
			System.out.print("Choose row: ");
			rowChoice = this.game.getInputHandler().nextInt();
			if (rowChoice < 1 || rowChoice > 5) {
				System.out.println("This row does not exist!");
			}
			if (!this.getBoard().getWall().checkIfRowIsSafeForColor(rowChoice - 1, chosenColor)) {
				isRowCorrect = false;
				System.out.println("You cannot put this color in that row!");
			}
		} while ((rowChoice < 1 || rowChoice > 5) || !isRowCorrect);
		return rowChoice - 1;
	}
	
	/**
	 * Allows a player to Surrender
	 * @param session
	 */
	void surrender(Session session) {

	}
}
