package rummikub.frames;

import rummikub.models.player.AI;
import rummikub.models.player.Human;
import rummikub.models.player.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MenuFrame extends JFrame {

    private final ArrayList<Player> players = new ArrayList<>();

    public static final Font TIMES_NEW_ROMAN_TITLE = new Font("Times new Roman", Font.PLAIN, 56);
    public static final Font TIMES_NEW_ROMAN_MID = new Font("Times new Roman", Font.PLAIN, 28);

    public MenuFrame() {
        super("Rummikub");
        Container con = getContentPane();
        con.add(new TitlePanel());
        con.add(new ButtonPanel(con));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
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

    private final class ButtonPanel extends JPanel {

        private int aiIdx = 1;
        private int humanIdx = 1;


        public ButtonPanel(Container con) {
            setBounds(100, 0, 600, 200);
            JButton AI = new JButton("Add AI");
            JButton PVP = new JButton("Add Human");
            JButton start = new JButton("Start");

            AI.setFont(TIMES_NEW_ROMAN_MID);
            PVP.setFont(TIMES_NEW_ROMAN_MID);
            start.setFont(TIMES_NEW_ROMAN_MID);

            setBackground(Color.BLACK);

            AI.setBackground(Color.BLACK);
            AI.setForeground(Color.WHITE);

            PVP.setBackground(Color.BLACK);
            PVP.setForeground(Color.WHITE);

            start.setBackground(Color.BLACK);
            start.setForeground(Color.WHITE);

            AI.addActionListener(e -> {
                players.add(new AI("AI" + aiIdx++));
            });

            PVP.addActionListener(e -> {
                players.add(new Human("Human" + humanIdx++));
            });

            start.addActionListener(e -> {
                if (players.size() >= 2 && players.size() <= 4) {
                    new GameFrame(players);
                    setVisible(false);
                    dispose();
                }
            });

            add(AI);
            add(PVP);
            add(start);

        }

    }

}
