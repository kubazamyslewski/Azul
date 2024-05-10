package main.azul;

import java.io.Serializable;

/**
 * Player's board representation.
 */
public class Board implements Serializable {

    private final Floor floor;
    private final Wall wall;
    private final Mosaic mosaic;
    private final Box linkedBox;
    private final Player owner;

    /**
     * Constructor for the Board class.
     * @param box - box of the current game session
     */
    public Board(Box box, Player owner) {
        this.owner = owner;
        this.floor = new Floor(this);
        this.mosaic = new Mosaic(this);
        this.wall = new Wall(this);
        this.linkedBox = box;
    }

    public void printBoard() {
        this.wall.printWall();
        this.mosaic.printMosaic();
        this.floor.displayFloor();
    }

    public Player getOwner() { return owner; }

    public Floor getFloor() { return floor; }

    public Wall getWall() { return wall; }

    public Mosaic getMosaic() { return mosaic; }

    public Box getLinkedBox() { return linkedBox; }

}
