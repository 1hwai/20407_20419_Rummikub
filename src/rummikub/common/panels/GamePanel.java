package rummikub.common.panels;

import rummikub.common.Table;
import rummikub.common.panels.buttons.NextTurnButton;
import rummikub.common.panels.buttons.ResetButton;
import rummikub.common.player.Human;
import rummikub.common.player.Player;
import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.Movement;
import rummikub.common.utils.Pointer;
import rummikub.common.utils.TileColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements GamePanelDrawer, ActionListener {

    public static final int SCREEN_WIDTH = 1800;
    public static final int SCREEN_HEIGHT = 900;

    private static final int FPS = 60;
    private static final int DELAY = 1000 / FPS;

    public static final int UNIT = 30;
    public static final Font COURIER_NEW = new Font("Courier new", Font.PLAIN, 28);
    private static final Color ON_HAND_COLOR = new Color(82, 155, 218, 122);

    private final Boolean running;

    private final ArrayList<BufferedImage> tileImages = new ArrayList<>();
    private BufferedImage plasticDeck = null;
    private NextTurnButton nextTurnBtn;
    private ResetButton resetBtn;

    private final Table table = new Table();
    private final Pointer pointer = new Pointer(table);

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new RummikubKeyAdapter());

        Timer timer = new Timer(DELAY, this);
        timer.start();
        running = true;

        initButtons();
        loadImages();
        MouseListener();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        drawIndicators(g);
        drawTable(g);
        drawPointer(g);
    }

    @Override
    public void drawIndicators(Graphics g) {
        g.setFont(COURIER_NEW);
        g.setColor(Color.white);
        g.drawString("Current Player : " + table.getCurrentPlayer().getId(), UNIT, UNIT);

        drawButtons(g);
    }

    public void drawPointer(Graphics g) {
//        int x1 = pointer.getI() * Tile.width;
//        int y1 = pointer.getJ() * Tile.height + Tile.height + 5;
//        g.setColor(Color.decode("#02cff7"));
//
//        g.drawLine(x1 - 5, y1, x1 + Tile.width + 5, y1);
    }

    @Override
    public void drawButtons(Graphics g) {
        g.drawImage(nextTurnBtn.getImage(), nextTurnBtn.position.x, nextTurnBtn.position.y,
                nextTurnBtn.width, nextTurnBtn.height, null);
        g.drawImage(resetBtn.getImage(), resetBtn.position.x, resetBtn.position.y,
                resetBtn.width, resetBtn.height, null);
    }

    @Override
    public void drawTable(Graphics g) {
        final int TABLE_WIDTH = GamePanel.SCREEN_WIDTH - UNIT * 2;
        int i = 0;
        int rowWidth = 0;
        for (TileList tileList : table.getTableList()) {
            int j = 0;
            int listWidth = 2 * UNIT * tileList.getList().size();
            if (rowWidth + listWidth > TABLE_WIDTH) {
                rowWidth = 0;
                i++;
            }
            for (Tile tile : tileList.getList()) {
                int x = rowWidth + UNIT + 2 * UNIT * j;
                int y = 3 * UNIT * (i + 1);
                g.drawImage(getTileImg(tile), x, y, Tile.width, Tile.height, null);
                if (table.getCurrentPlayer().getOnHand().getList().contains(tile)) {
                    g.setColor(ON_HAND_COLOR);
                    g.fillRect(x, y, Tile.width, Tile.height);
                }
                if (pointer.isTile(tile)) {
                    g.setColor(Color.white);
                    g.drawLine(x, y + Tile.height + 5, x + Tile.width, y + Tile.height + 5);
                }
                j++;
            }
            rowWidth += listWidth + 20;
        }

        drawPlayerDeck(g, table.getCurrentPlayer());
    }

    @Override
    public void drawPlayerDeck(Graphics g, Player player) {
        final int WIDTH = GamePanel.SCREEN_WIDTH * 3 / 5; // 1080
        final int HEIGHT = WIDTH / 4;   // 270
        int x = (GamePanel.SCREEN_WIDTH - WIDTH) / 2;
        int y = GamePanel.SCREEN_HEIGHT - HEIGHT;

        g.drawImage(plasticDeck, x, y, WIDTH, HEIGHT, null);

        if (player instanceof Human human) {
            TileList deck = human.getDeck();
            int i = 0;
            for (Tile tile : deck.getList()) {
                int x1 = x + 2 * UNIT * (i % 18);
                int y1 = y + HEIGHT * (i / 18) / 3;
                g.drawImage(getTileImg(tile), x1, y1, Tile.width, Tile.height, null);
                if (table.getCurrentPlayer().getOnHand().getList().contains(tile)) {
                    g.setColor(ON_HAND_COLOR);
                    g.fillRect(x1, y1, Tile.width, Tile.height);
                }
                if (pointer.isTile(tile)) {
                    g.setColor(Color.BLACK);
                    g.drawLine(x1, y1 + Tile.height + 5, x1 + Tile.width + 5, y1 + Tile.height + 5);
                }
                i++;
            }
            if (deck.getList().isEmpty()) {
                g.drawLine(x, y + Tile.height + 5,  x + Tile.width + 5, y + Tile.height + 5);
            }
        }
    }

    private void initButtons() {
        nextTurnBtn = new NextTurnButton(new Point(SCREEN_WIDTH - 200, SCREEN_HEIGHT - 200));
        resetBtn = new ResetButton(new Point(100, SCREEN_HEIGHT - 200));
    }

    private void loadImages() {
        try {
            final String SOURCE = "/resources/tiles/";
            for (TileColor color : TileColor.values()) {
                String colorCode = color.getColorCode();
                for (int i = 1; i < 14; i++) {
                    tileImages.add(ImageIO.read(getClass().getResource(SOURCE + colorCode + i + ".png")));
                    System.out.println(SOURCE + colorCode + i + ".png" + " has been Successfully loaded");
                }
            }
            tileImages.add(ImageIO.read(getClass().getResource(SOURCE + "RED_JOKER" + ".png")));
            tileImages.add(ImageIO.read(getClass().getResource(SOURCE + "WHITE_JOKER" + ".png")));
            System.out.println(SOURCE + "RED_JOKER" + ".png" + " has been Successfully loaded");
            System.out.println(SOURCE + "WHITE_JOKER" + ".png" + " has been Successfully loaded");

            plasticDeck = ImageIO.read(getClass().getResource("/resources/utils/plasticDeck.png"));
            System.out.println("/resources/utils/plasticDeck.png has been Successfully loaded");

            nextTurnBtn.setImage("/resources/indicators/nextTurnBtn.png");
            System.out.println("/resources/indicators/nextTurnBtn.png has been Successfully loaded");
            resetBtn.setImage("/resources/indicators/resetBtn.png");
            System.out.println("/resources/indicators/resetBtn.png has been Successfully loaded");

        } catch (IOException e) {
            System.out.println("IOException :");
            e.printStackTrace();
        }

    }

    /**
     * @author 1hwai
     * @param tile  The tile that is wanted to get the image of it.
     * @return {@code BufferedImage} image of the tile.
     */
    private BufferedImage getTileImg(Tile tile) {
        if (tile.number == 0) {
            if (tile.color == TileColor.WHITE) return tileImages.get(tileImages.size() - 1);
            else return tileImages.get(tileImages.size() - 2);
        }

        int i = 0;
        for (TileColor c : TileColor.values()) {
            if (c == tile.color) break;
            i++;
        }

        return tileImages.get(13 * i + tile.number - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
        }
        repaint();
    }

    private void MouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point mp = new Point(e.getX(), e.getY());

                nextTurnBtn.ifButtonClicked(mp, () -> {
                    table.next();
                    pointer.init();
                    return null;
                });
                resetBtn.ifButtonClicked(mp, () -> {
                    return null;
                });
            }
        });
    }

    private final class RummikubKeyAdapter extends KeyAdapter {

        public RummikubKeyAdapter() {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Q -> pointer.move(Movement.QUICK_LEFT);
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> pointer.move(Movement.LEFT);
                case KeyEvent.VK_E -> pointer.move(Movement.QUICK_RIGHT);
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> pointer.move(Movement.RIGHT);

                case KeyEvent.VK_SHIFT -> {
                    table.getCurrentPlayer().setOnHand(pointer.getTile());
                    System.out.println();
                    table.getCurrentPlayer().getOnHand().getList().forEach(t -> System.out.println(t.color + " " + t.number));
                    System.out.println();
                }

                case KeyEvent.VK_SPACE -> pointer.swapSide();
            }
        }
    }
}
