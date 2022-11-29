package rummikub.frames;

import rummikub.models.player.AI;
import rummikub.models.player.Human;
import rummikub.models.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuFrame extends JFrame {

    private final ArrayList<Player> players = new ArrayList<>();

    public static final Font TIMES_NEW_ROMAN_TITLE = new Font("Times new Roman", Font.PLAIN, 56);
    public static final Font TIMES_NEW_ROMAN_MID = new Font("Times new Roman", Font.PLAIN, 28);

    public MenuFrame() {
        super("Rummikub");
        Container con = getContentPane();
        con.add(new PlayersPanel());
        con.add(new ButtonPanel(con));
        con.add(new TitlePanel());

        con.repaint();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        setSize(800, 600);

    }

    private final class TitlePanel extends JPanel {
        public TitlePanel() {
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


        public ButtonPanel(Container con) {
            setBounds(0, 400, 800, 300);
            JButton AI = new JButton("Add AI");
            JButton PVP = new JButton("Add Human");
            JButton reset = new JButton("Reset");
            JButton start = new JButton("Start");
            JTextField idField = new JTextField("id", 10);

            AI.setFont(TIMES_NEW_ROMAN_MID);
            PVP.setFont(TIMES_NEW_ROMAN_MID);
            reset.setFont(TIMES_NEW_ROMAN_MID);
            start.setFont(TIMES_NEW_ROMAN_MID);
            idField.setFont(TIMES_NEW_ROMAN_MID);

            setBackground(Color.BLACK);

            AI.setBackground(Color.BLACK);
            AI.setForeground(Color.WHITE);

            PVP.setBackground(Color.BLACK);
            PVP.setForeground(Color.WHITE);

            reset.setBackground(Color.BLACK);
            reset.setForeground(Color.WHITE);

            start.setBackground(Color.BLACK);
            start.setForeground(Color.WHITE);

            idField.setBackground(Color.BLACK);
            idField.setForeground(Color.WHITE);

            AI.setBounds(0, 0, 200, 100);
            PVP.setBounds(200, 0, 200, 100);
            reset.setBounds(400, 0, 200, 100);
            start.setBounds(600, 0, 200, 100);

            idField.setBounds(200, 100, 300, 100);

            AI.addActionListener(e -> {
                if (players.size() < 4) {
                    players.add(new AI("AI" + aiIdx++));
                    con.repaint();
                }
            });

            PVP.addActionListener(e -> {
                if (players.size() < 4) {
                    String id = idField.getText();
                    boolean idAlreadyExists = false;
                    for (Player player : players) {
                        if (player.getId().equals(id)) {
                            idAlreadyExists = true;
                            break;
                        }
                    }
                    if (idAlreadyExists) {
                        alert();
                    } else {
                        players.add(new Human(id));
                        con.repaint();
                    }
                }
            });

            reset.addActionListener(e -> {
                players.clear();
                aiIdx = 1;
                con.repaint();
            });

            start.addActionListener(e -> {
                if (players.size() >= 2 && players.size() <= 4) {
                    new GameFrame(players);
                    setVisible(false);
                    dispose();
                }
            });

            add(AI);
            add(idField);
            add(PVP);
            add(reset);
            add(start);

        }

        public void alert() {
            JOptionPane.showMessageDialog(null, "ID already in use", "WARNING",JOptionPane.ERROR_MESSAGE);
        }

    }

    private final class PlayersPanel extends JPanel implements ActionListener {

        private static final int WIDTH = 200, HEIGHT = 800;

        public PlayersPanel() {
            setBounds(200, 150, 400, 200);
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);
            setForeground(Color.WHITE);
            setFocusable(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

        private void draw(Graphics g) {
            int y = 20;
            for (Player player : players) {
                g.setFont(TIMES_NEW_ROMAN_MID);
                g.setColor(Color.WHITE);
                g.drawString(player.getId(), 0, y);
                y+=30;
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
            System.out.println("fsdfsf");
        }
    }

}
