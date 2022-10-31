package rummikub;

import rummikub.common.panels.GamePanel;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        super("Rummikub");
        this.add(new GamePanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
