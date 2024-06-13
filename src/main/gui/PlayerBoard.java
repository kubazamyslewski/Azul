package main.gui;

import main.azul.Mosaic;
import main.azul.Player;
import main.azul.Tile;
import main.azul.Wall;
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
    private JPanel panel;
    private JLabel scoreMarker;
    private List<JButton> buildRowButtons;
    private int playerID;
    private Image backgroundImage;
    private GameSession gameSession;
    private Timer timer;

    public PlayerBoard(Player m, GameSession gameSession) {
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

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        panel.setLayout(null);

        this.loadBoard();

        this.setContentPane(panel);
        this.setVisible(true);

        // Initialize the timer
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        timer.start();
    }

    private void loadBoard() {
        Mosaic mosaic = this.gameSession.getPlayers()[this.gameSession.getIndexFromPlayerID(model.getPlayerID())].getBoard().getMosaic();
        Wall wall = this.gameSession.getPlayers()[this.gameSession.getIndexFromPlayerID(model.getPlayerID())].getBoard().getWall();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 1; j++) {
                Tile t = wall.getWallMap().get(i)[j];
                String color;
                if (t == null) {
                    color = "BLANK";
                } else {
                    color = t.getColor();
                }
                TileGUI tileGUI = new TileGUI(color, j, i, gameSession);
                buildRowButtons.add(tileGUI);
                tileGUI.setBounds(317 - j * 67, 267 + i * 67);
                panel.add(tileGUI);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String color;
                try {
                    color = mosaic.getPlacedTile(i, j).getColor();
                } catch (NullPointerException e) {
                    color = "BLANK";
                }
                String c = color;
                JPanel p = new JPanel() {
                    private Image backgroundImage;

                    {
                        try {
                            URL url = getClass().getResource("/images/" + c + ".png");
                            backgroundImage = ImageIO.read(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        if (backgroundImage != null) {
                            g.drawImage(backgroundImage, 0, 0, this);
                        }
                    }
                };
                if (!c.equals("BLANK")) {
                    p.setVisible(true);
                } else {
                    p.setVisible(false);
                }
                p.setBounds(417 + j * 67, 267 + (i * 67), 60, 60);
                panel.add(p);
            }
        }
    }

    private void update() {
        panel.removeAll();
        this.loadBoard();
        panel.revalidate();
        panel.repaint();
    }

    public void setInactive() {
        if (gameSession.getCurrentPlayer().getPlayerID() != model.getPlayerID()) {
            JLabel label = new JLabel("not your turn");
            label.setBounds(400, 400, 100, 100);
            label.setOpaque(true);
            label.setBackground(Color.RED);
            this.add(label);
            label.setVisible(true);
        }
        this.revalidate();
        this.repaint();
    }
}
