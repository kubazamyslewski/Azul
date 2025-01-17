package main.azul;

import main.Exceptions.ColorNotInTheMiddleException;
import main.Exceptions.WrongTileColourException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * An area in the middle of the table where leftover tiles from workshops go
 */
public class Middle implements Storage, Serializable {

    /**
     * Map representing contents of the middle.
     */
    private final Map<Tile, Stack<Tile>> middleMap;

    private final TileDrawingPool parentTileDrawingPool;

    /**
     * Constructor for the middle.
     */
    public Middle(TileDrawingPool parentTileDrawingPool) {
        this.parentTileDrawingPool = parentTileDrawingPool;
        middleMap = new HashMap<>();
        for (Tile color : Tile.values()) {
            middleMap.put(color, new Stack<>());
        }
    }

    public TileDrawingPool getParentTileDrawingPool() { return this.parentTileDrawingPool; }

    /**
     * Adds a tile of a specified color to the middle.
     * @param color color to be added
     */
    public void add(Tile color) { this.middleMap.get(color).push(color); }

    /**
     * Gets a specified color of the tiles from the middle and adds them to the players wall
     * @param color - color of the tiles to be extracted from the middle
     * @param player - player taking the tiles
     * @throws WrongTileColourException - if specified color is FIRST tile type
     */
    public void getTileColorFromMiddle(Player player, Tile color, int row) throws WrongTileColourException, ColorNotInTheMiddleException{
        if (color.equals(Tile.FIRST)) throw new WrongTileColourException("First tile cannot be drawn alone!");
        if(hasColor(Tile.FIRST)) player.getBoard().getFloor().addTileToFloor(middleMap.get(Tile.FIRST).pop());
        if(hasColor(color)) {
            while(!middleMap.get(color).isEmpty()) {
                player.getBoard().getWall().addToTempStorage(middleMap.get(color).pop());
            }
        } else throw new ColorNotInTheMiddleException("This color is not present in the middle!");

        player.getBoard().getWall().addTilesToWall(row);
    }

    /**
     * Retrieves the amount of tiles of a specified color that are present in the middle
     * @param color - color of the tiles to be counted
     * @return amount of tiles of specified color
     */
    @Override
    public int getTileQuantity(Tile color){ return this.middleMap.get(color).size(); }

    /**
     * Checks whether tiles of specified color are present in the middle
     * @param color - color of the tiles to be checked for
     * @return boolean stating whether the tiles of specified color are present
     */
    @Override
    public boolean hasColor(Tile color) {
        if (this.middleMap.get(color) == null) {
            return false;
        } else {
            return !this.middleMap.get(color).isEmpty();
        }
    }


    /**
     * Checks whether the middle is empty.
     * @return boolean stating whether the middle is empty
     */
    @Override
    public boolean isEmpty(){
        for (Stack<Tile> tileStack : middleMap.values()) {
            if (!tileStack.isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public boolean colorsEmpty(){
        return !hasColor(Tile.BLACK) && !hasColor(Tile.WHITE) && !hasColor(Tile.RED) && !hasColor(Tile.YELLOW) && !hasColor(Tile.BLUE);
    }
}