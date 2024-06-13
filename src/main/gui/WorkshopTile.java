package main.gui;

import main.azul.Player;
import main.azul.Workshop;
import main.client.GameSession;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class WorkshopTile extends JButton{
    private String color;
    private int workshopID;
    private GameSession gameSession;

    public WorkshopTile(String color, GameSession gameSession) {
        this.gameSession = gameSession;
        this.color = color;

        this.setSize(60, 60);
        try {
            URL url = getClass().getResource("/images/" + color + ".png");
            Image img = ImageIO.read(url);
            this.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClicked();
            }
        });
    }

    public int getWorkshopID() {
        return workshopID;
    }

    public void setWorkshopID(int workshopID) {
        this.workshopID = workshopID;
    }

    public void setBounds(int x, int y) {
        this.setBounds(x, y, this.getWidth(), this.getHeight());
    }

    // This method will be called when the button is clicked
    private void buttonClicked() {
        // Code to execute when the button is clicked
        System.out.println("workshopID " + workshopID + " clicked! Color: " + color);
        Player p = gameSession.getCurrentPlayer();
        if(workshopID==6){
            p.setWorkshopClicked(true);
            p.setTilePool("middle");
        }
        else{
            p.setWorkshopChoice(workshopID+1);
            p.setWorkshopClicked(true);
            p.setTilePool("workshop");
        }

//        for (Player player : gameSession.getPlayers()) {
//            player.setWorkshopChoice(workshopID+1);
//            player.setWorkshopClicked(true);
//            player.setTilePool("workshop");
//        }
        switch (color) {
            case "BLUE":
                p.setColorChoice(5);
//                for (Player player : gameSession.getPlayers()) {
//                    player.setColorChoice(5);
//                }
                break;
            case "BLACK":
                p.setColorChoice(1);
//                for (Player player : gameSession.getPlayers()) {
//                    player.setColorChoice(1);
//                }
                break;
            case "RED":
                p.setColorChoice(3);
//                for (Player player : gameSession.getPlayers()) {
//                    player.setColorChoice(3);
//                }
                break;
            case "WHITE":
                p.setColorChoice(2);
//                for (Player player : gameSession.getPlayers()) {
//                    player.setColorChoice(2);
//                }
                break;
            case "YELLOW":
                p.setColorChoice(4);
//                for (Player player : gameSession.getPlayers()) {
//                    player.setColorChoice(4);
//                }
                break;
        }
    }
}
