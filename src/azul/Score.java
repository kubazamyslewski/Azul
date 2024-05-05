package azul;


import Exceptions.IncorrectAmountOfTilesOnFloorException;

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
		player.setPlayerScore(0);
	}
	
	/**
	 * Gets the current winner
	 * @return player with the highest score
	 */
	public Player getCurrentWinner() {
		if (players == null || players.length == 0) {
			return null;
		}
		Player currentWinner = players[0];
		for (Player p : players) {
			if(p.getPlayerScore() > currentWinner.getPlayerScore()) {
				currentWinner = p;
			}
		}
		return currentWinner;
	}
	
	/**
	 * Rewards a player with points when they place a new tile
	 * @param player
	 */
	public void scoreNewTile(Player player) {
		
	}
	
	
	/**
	 * Rewards a player with points for every full row
	 * @param player
	 */
	public void scoreRows(Player player) {
		if(player.getBoard().getMosaic().countCompletedRows()>0) {
			player.setPlayerScore(player.getPlayerScore() + (2 * player.getBoard().getMosaic().countCompletedRows()));
		}
	}
	
	/**
	 * Rewards a player with points for every full column
	 * @param player
	 */
	public void scoreColumns(Player player) {
		if(player.getBoard().getMosaic().countCompletedColumns()>0) {
			player.setPlayerScore(player.getPlayerScore() + (7 * player.getBoard().getMosaic().countCompletedColumns()));
		}
	}
	
	/**
	 * Rewards a player with points for every full color
	 * @param player
	 */
	public void scoreFullColors(Player player) {
		int fullColors = 0;
		for (Tile color: Tile.values()){
			boolean colorInAllRows = true;
			for (int row=0; row < 5; row++){
				boolean colorInRow = player.getBoard().getMosaic().checkRowForColor(row, color);
				if (!colorInRow) {
					colorInAllRows = false;
					break;
				}
			}
			if (colorInAllRows) {
				fullColors++;
			}
		}
		player.setPlayerScore(player.getPlayerScore() + (10 * fullColors));
	}

	/**
	 * Rewards a player with points for every full row, column and color
	 * (call only once at the end of the game)
	 * @param player player to be rewarded with points
	 */
	public void addEndGamePoints(Player player) {
		scoreRows(player);
		scoreColumns(player);
		scoreFullColors(player);
	}

	/**
	 * Takes points from players for tiles on the floor 
	 * @param player
	 */
	public void applyFloorPenalty(Player player) throws IncorrectAmountOfTilesOnFloorException {
		int floorTiles = player.getBoard().getFloor().getAmountOfTiles();
		if (floorTiles > 0 && floorTiles <= 2) {
			player.setPlayerScore(player.getPlayerScore() - floorTiles);
		} else if (floorTiles > 2 && floorTiles <= 5) {
			player.setPlayerScore(player.getPlayerScore() - (2 * (floorTiles - 2) + 2));
		}
		else if (floorTiles > 5 && floorTiles <= 7) {
			player.setPlayerScore(player.getPlayerScore() - (3 * (floorTiles - 5) + 8));
		}
		else throw new IncorrectAmountOfTilesOnFloorException("Too many tiles on the floor!");
	}
}
