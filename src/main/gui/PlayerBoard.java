package main.gui;

import main.azul.Board;
import main.azul.Player;
import main.client.GameSession;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoard extends JFrame {
    private Player model;
    private JLabel scoreMarker;
    private List<JButton> buildRowButtons;
    private int playerID;
    private Image backgroundImage;
    private GameSession gameSession;

    public PlayerBoard(Player m, GameSession gameSession){
        this.gameSession = gameSession;
        buildRowButtons = new ArrayList<>();
        model = m;
        this.setSize(800, 800);
        String name = "Player " + m.getPlayerID();
        this.setTitle(name);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        try {
            URL url = getClass().getResource("/images/PlayerBoard.png");
            backgroundImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        panel.setLayout(null);

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < i+1; j++){
                TileGUI tileGUI = new TileGUI("BLANK",j,i, gameSession);
                buildRowButtons.add(tileGUI);
                tileGUI.setBounds(317-j*67, 267+i*67);
                panel.add(tileGUI);
            }
        }

        this.setContentPane(panel);
        this.setVisible(true);
    }
}