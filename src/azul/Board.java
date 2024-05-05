package azul;

/**
 * Player's board representation.
 */
public class Board {

    private final Floor floor;
    private final Wall wall;
    private final Mosaic mosaic;
    private final Box linkedBox;

    /**
     * Constructor for the Board class.
     * @param box - box of the current game session
     */
    public Board(Box box) {
        this.floor = new Floor(this);
        this.mosaic = new Mosaic(this);
        this.wall = new Wall(this);
        this.linkedBox = box;
    }

    public Floor getFloor() { return floor; }

    public Wall getWall() { return wall; }

    public Mosaic getMosaic() { return mosaic; }

    public Box getLinkedBox() { return linkedBox; }

}
