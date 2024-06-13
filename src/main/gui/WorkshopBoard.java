package main.gui;

import main.Exceptions.FirstTileInWorkshopException;
import main.azul.Tile;
import main.azul.TileDrawingPool;
import main.azul.Workshop;
import main.client.GameSession;

import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class WorkshopBoard extends JFrame {
    JPanel panel;
    private Image backgroundImage;
    private TileDrawingPool linkedTileDrawingPool;
    private GameSession gameSession;
    public WorkshopBoard(TileDrawingPool linkedTileDrawingPool, GameSession gameSession) throws FirstTileInWorkshopException {
        this.linkedTileDrawingPool = linkedTileDrawingPool;
        this.gameSession = gameSession;
        this.setSize(800, 800);

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            URL url = getClass().getResource("/images/WorkshopGUI.png");
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
        loadTiles();

        this.setContentPane(panel);
        this.setVisible(true);
    }
    public void update(TileDrawingPool linkedTileDrawingPool) throws FirstTileInWorkshopException {
        this.linkedTileDrawingPool = linkedTileDrawingPool;
        panel.removeAll();
        loadTiles();
        panel.revalidate();
        panel.repaint();
    }
    private List<WorkshopTile> getTilesFromWorkspace(Workshop w) throws FirstTileInWorkshopException {
        List<WorkshopTile> tiles = new ArrayList<>();
        Tile[] colors = {Tile.BLUE, Tile.BLACK, Tile.RED,Tile.WHITE,Tile.YELLOW};
        for (Tile color : colors) {
            int quantity = w.getTileQuantity(color);
            for (int i = 0; i < quantity; i++) {
                tiles.add(new WorkshopTile(color.toString(), gameSession));
            }
        }
        return tiles;
    }
    private List<WorkshopTile> getTilesFromMiddle(){
        List<WorkshopTile> tiles = new ArrayList<>();
        Tile[] colors = {Tile.BLUE, Tile.BLACK, Tile.RED,Tile.WHITE,Tile.YELLOW};
        for (Tile color : colors) {
            int quantity = linkedTileDrawingPool.getMiddle().getTileQuantity(color);
            for (int i = 0; i < quantity; i++) {
                tiles.add(new WorkshopTile(color.toString(), gameSession));
            }
        }
        return tiles;
    }
    public void loadTiles() throws FirstTileInWorkshopException {
        for(int i = 0; i < 5; i++) {
            int x = 0;
            int y = 0;
            switch (i) {
                case 0:
                    x = 80;
                    y = 240;
                    break;
                case 1:
                    x = 600;
                    y = 250;
                    break;
                case 2:
                    x = 340;
                    y = 60;
                    break;
                case 3:
                    x = 200;
                    y = 550;
                    break;
                case 4:
                    x = 470;
                    y = 550;
                    break;
            }
            int j = 0;
            for (WorkshopTile t : getTilesFromWorkspace(linkedTileDrawingPool.getWorkshops()[i])) {
                switch (j){
                    case 0:t.setBounds(x, y);break;
                    case 1:t.setBounds(x+60, y);break;
                    case 2:t.setBounds(x, y+60);break;
                    case 3:t.setBounds(x+60, y+60);break;
                }
                t.setWorkshopID(i);
                panel.add(t);
                j++;
            }
            int w=0;
            for(WorkshopTile t : getTilesFromMiddle()){
                t.setBounds((w%2)*60+300, (w%2)*60+300);
                panel.add(t);
                j++;
            }
        }
    }
}
