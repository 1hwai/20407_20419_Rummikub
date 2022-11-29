package rummikub.frames;

import rummikub.models.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FinishFrame extends JFrame {

    public static final Font TIMES_NEW_ROMAN_TITLE = new Font("Times new Roman", Font.PLAIN, 56);
    public static final Font TIMES_NEW_ROMAN_MID = new Font("Times new Roman", Font.PLAIN, 28);

    public FinishFrame(Player winner) {
        super("Rummikub");
        Container con = getContentPane();
        con.add(new TitlePanel());
        con.add(new GotoMenuPanel());
        con.add(new NamePanel(winner));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        setSize(800, 450);

    }

    private final class TitlePanel extends JPanel {
        public TitlePanel() {
            setBounds(100, 100, 600, 150);
            setBackground(Color.BLACK);

            JLabel titleLabel = new JLabel("Rummikub");
            titleLabel.setBackground(Color.BLACK);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(TIMES_NEW_ROMAN_TITLE);

            add(titleLabel);
        }
    }

    private final class NamePanel extends JPanel {
        public NamePanel(Player winner) {
            setBounds(100, 100, 600, 150);
            setBackground(Color.BLACK);

            System.out.println(winner.getId());

            JLabel titleLabel = new JLabel("Winner : " + winner.getId());
            titleLabel.setBackground(Color.BLACK);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(TIMES_NEW_ROMAN_TITLE);

            add(titleLabel);
        }
    }

    private final class GotoMenuPanel extends JPanel {
        public GotoMenuPanel() {
            setBounds(100, 250, 600, 150);
            setBackground(Color.BLACK);
            JButton gotoMenu = new JButton("Go to Menu...");
            gotoMenu.setBackground(Color.BLACK);
            gotoMenu.setForeground(Color.WHITE);
            gotoMenu.setFont(TIMES_NEW_ROMAN_MID);

            gotoMenu.addActionListener(e -> {
                new MenuFrame();
                setVisible(false);
                dispose();
            });

            add(gotoMenu);
        }
    }

}
