package azul;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * An area in the middle of the table where leftover tiles from workshops go
 */
public class Middle implements Storage {

    private Map<Tile, Stack<Tile>> middleMap;

    public Middle() {
        middleMap = new HashMap<>();
        for (Tile color : Tile.values()) {
            middleMap.put(color, new Stack<>());
        }
    }

    public void add(Tile color) {
        Stack<Tile> tileStack = middleMap.get(color);
        tileStack.push(color);
    }

    @Override
    public int getTileQuantity(Tile color){
        Stack<Tile> tileStack = middleMap.get(color);
        return tileStack.size();
    }

    @Override
    public boolean hasColor(Tile color){
        Stack<Tile> tileStack = middleMap.get(color);
        return !tileStack.isEmpty();
    }

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