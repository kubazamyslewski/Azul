package main.client;

import main.azul.Player;
import main.azul.Tile;

import java.io.Serializable;

public class Move implements Serializable {

  private String workshopOrMiddle;

  public String getWorkshopOrMiddle() {
    return workshopOrMiddle;
  }

  public int getRow() {
    return row;
  }

  public Tile getColor() {
    return color;
  }

  public int getPerformer() {
    return performerID;
  }

  public int getChosenWorkshop() {
    return this.chosenWorkshop;
  }

  private int chosenWorkshop;
  private int row;
  private Tile color;
  private int performerID;

  public Move(String workshopOrMiddle, int chosenWorkshop, Tile color, int row, int performer) {
    this.workshopOrMiddle = workshopOrMiddle;
    this.chosenWorkshop = chosenWorkshop;
    this.row = row;
    this.color = color;
    this.performerID = performer;
  }
}
