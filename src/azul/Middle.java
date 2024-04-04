package azul;

import java.util.List;

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