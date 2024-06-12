package main.gui;

import main.azul.Workshop;

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

    public WorkshopTile(String color) {
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
    }
}
