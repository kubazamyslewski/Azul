package main.gui;

import main.azul.Player;
import main.client.GameSession;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class TileGUI extends JButton {
    private String color;
    private int row;
    private int col;
    private GameSession gameSession;

    public TileGUI(String color, int x, int y, GameSession gameSession) {
        this.gameSession = gameSession;
        this.row = y;
        this.col = x;
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

    public void setBounds(int x, int y) {
        this.setBounds(x, y, this.getWidth(), this.getHeight());
    }

    // This method will be called when the button is clicked
    private void buttonClicked() {
        // Code to execute when the button is clicked
        System.out.println("Button at row " + row + " and column " + col + " clicked!");
        for (Player player : gameSession.getPlayers()) {
            player.setRowChoice(row+1);
            player.setRowClicked(true);
        }

    }
}
