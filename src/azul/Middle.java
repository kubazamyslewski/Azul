package azul;

import Exceptions.ColorNotInTheMiddleException;
import Exceptions.WrongTileColourException;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * An area in the middle of the table where leftover tiles from workshops go
 */
public class Middle implements Storage {

    /**
     * Map representing contents of the middle.
     */
    private final Map<Tile, Stack<Tile>> middleMap;

    /**
     * Constructor for the middle.
     */
    public Middle() {
        middleMap = new HashMap<>();
        for (Tile color : Tile.values()) {
            middleMap.put(color, new Stack<>());
        }
    }

    /**
     * Adds a tile of a specified color to the middle.
     * @param color
     */
    public void add(Tile color) { this.middleMap.get(color).push(color); }

    /**
     * Gets a specified color of the tiles from the middle and adds them to the players wall
     * @param color - color of the tiles to be extracted from the middle
     * @param wall - reference to wall to which the tiles of specified color from the middle should be added
     * @param floor - reference to floor to which first tile should be added in case in which it is present
     *                in the middle
     * @throws WrongTileColourException - if specified color is FIRST tile type
     */
    public void getTileColorFromMiddle(Tile color, Wall wall, Floor floor) throws WrongTileColourException, ColorNotInTheMiddleException{
        if (color.equals(Tile.FIRST)) throw new WrongTileColourException("First tile cannot be drawn alone!");
        if(hasColor(color)) {
            while(middleMap.get(color).isEmpty()) {
                wall.addToTempStorage(middleMap.get(color).pop());
            }
        } else throw new ColorNotInTheMiddleException("This color is not present in the middle!");
        if(hasColor(Tile.FIRST)) floor.addTileToFloor(middleMap.get(Tile.FIRST).pop());
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
    public boolean hasColor(Tile color){ return this.middleMap.get(color).isEmpty(); }

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

}