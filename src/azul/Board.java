package azul;

/**
 * Player's board representation.
 */

public class Board {
    private Floor floor;
    private Wall wall;
    private Mosaic mosaic;
    Box linkedBox;

    /**
     * Constructor for the Board class.
     */
    public Board() {
        this.floor = new Floor(this);
        this.mosaic = new Mosaic();
        this.linkedBox = new Box();
        this.wall = new Wall(this, linkedBox, mosaic);
    }

    public Floor getFloor() {
        return floor;
    }

    public Wall getWall() {
        return wall;
    }

}
