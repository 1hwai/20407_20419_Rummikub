package rummikub.frames;

import rummikub.models.player.Player;
import rummikub.panels.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GameFrame extends JFrame {
    public GameFrame(ArrayList<Player> players) {
        super("Rummikub");
        add(new GamePanel(players) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    if (table.hasFinished) {
                        running = false;
                        new FinishFrame(table.winner);
                        setVisible(false);
                        dispose();
                    }
                }
                repaint();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

}
