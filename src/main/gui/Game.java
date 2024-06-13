package main.gui;

import main.Exceptions.FirstTileInWorkshopException;
import main.azul.Board;
import main.azul.Player;
import main.client.GameSession;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private GameSession gameSession;
    private List<PlayerBoard> playerBoards;
    private WorkshopBoard workshopBoard;
    public Game() throws FirstTileInWorkshopException {
        playerBoards = new ArrayList<>();
        gameSession = new GameSession();
        initializeWorkshop();
        for(int i = 0; i < gameSession.getPlayerCount(); i++){
            initializeBoard(gameSession.getPlayers()[i]);
        }
        while (!gameSession.isOver()) {
            try{
                gameSession.runRound();
                gameSession.checkIfOver();
                if (!gameSession.isOver()) {
                    gameSession.setupNewRound();
                }
            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        gameSession.applyFinishingScore();
    }
    public void initializeWorkshop() throws FirstTileInWorkshopException {
        workshopBoard = new WorkshopBoard(gameSession.getLinkedTileDrawingPool(), gameSession);
    }
    public void initializeBoard(Player i){
        playerBoards.add(new PlayerBoard(i, gameSession));
    }
    public static void main(String[] args) throws FirstTileInWorkshopException {
        new Game();
    }


}
