package main.gui;

import main.azul.Mosaic;
import main.azul.Player;
import main.azul.Tile;
import main.azul.Wall;
import main.azul.Floor;
import main.client.GameSession;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    private JLabel wanderingTile;
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
        wanderingTile = new JLabel();
        try {
            URL tileUrl = getClass().getResource("/images/black_tile.png");
            Image tileImage = ImageIO.read(tileUrl);
            wanderingTile.setIcon(new ImageIcon(tileImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
        wanderingTile.setBounds(10, 10, 50, 50); // Initial position and size
        panel.add(wanderingTile);

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
        Floor floor = this.gameSession.getPlayers()[this.gameSession.getIndexFromPlayerID(model.getPlayerID())].getBoard().getFloor();

        int tileSize = 60;
        int offsetX = 317;
        int offsetY = 267;

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
                tileGUI.setBounds(offsetX - j * 71, offsetY + i * 68, tileSize, tileSize);
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
                            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                        }
                    }
                };
                if (!c.equals("BLANK")) {
                    p.setVisible(true);
                } else {
                    p.setVisible(false);
                }
                p.setBounds(417 + j * 67, offsetY + (i * 67), tileSize, tileSize);
                panel.add(p);
            }
        }

        // Add floor tiles with increased spacing
        int floorTileSize = 60;
        int floorSpacing = 14; // Adjust this value to change the spacing between floor tiles
        Tile[] floorTiles = floor.getTilesOnTheFloor();
        for (int i = 0; i < floorTiles.length; i++) {
            if (floorTiles[i] != null) {
                String color = floorTiles[i].getColor();
                JLabel floorTile = new JLabel();
                try {
                    File file = new File("resources/images/" + color + ".png");
                    Image tileImage = ImageIO.read(file);
                    floorTile.setIcon(new ImageIcon(tileImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                floorTile.setBounds(37 + i * (floorTileSize + floorSpacing), 655, floorTileSize, floorTileSize); // Position the tiles at the bottom
                panel.add(floorTile);
            }
        }

        updateWanderingTile();
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

    private void updateWanderingTile() {
        int score = model.getPlayerScore() - 1;
        if (score < 0) {
            wanderingTile.setVisible(false);
            return;
        }
        wanderingTile.setVisible(true);
        // Calculate the x and y positions based on the score
        int xPosition = 40 + (score % 20) * 35; // Assuming each score box is 35 pixels wide
        int yPosition = 40 + (score / 20) * 35; // Adjusting for the row change after every 20 points

        wanderingTile.setBounds(xPosition, yPosition, 25, 25); // Update position based on score
        panel.add(wanderingTile); // Re-add the wandering tile to the panel
    }
}
