package rummikub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    private static final int FPS = 60;
    private static final int DELAY = 1000 / FPS;

    private Boolean running = false;
    private Timer timer;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new RummikubKeyAdapter());
        init();
    }

    public void init() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
        }
        repaint();
    }

    public static class RummikubKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("keycode : " + e.getKeyCode());
        }
    }
}
