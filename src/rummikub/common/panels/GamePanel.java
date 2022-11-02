package rummikub.common.panels;

import rummikub.common.RummikubKeyAdapter;
import rummikub.common.Table;
import rummikub.common.player.Human;
import rummikub.common.player.Player;
import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
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
    public static final int SCREEN_HEIGHT = 1200;

    private static final int FPS = 60;
    private static final int DELAY = 1000 / FPS;

    public static final int UNIT = 30;
    public static final Font COURIER_NEW = new Font("Courier new", Font.PLAIN, 28);

    private final Boolean running;

    private final ArrayList<BufferedImage> tileImages = new ArrayList<>();
    private BufferedImage plasticDeck = null;

    private final Table table = new Table();

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new RummikubKeyAdapter());

        Timer timer = new Timer(DELAY, this);
        timer.start();
        running = true;

        loadImages();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        drawIndicators(g);
        drawTable(g);
    }

    @Override
    public void drawIndicators(Graphics g) {
        g.setFont(COURIER_NEW);
        g.setColor(Color.white);
        g.drawString("Current Player : " + table.getCurrentPlayer().getId(), UNIT, UNIT);
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
                g.drawImage(getTileImg(tile), rowWidth + UNIT + 2 * UNIT * j, 3 * UNIT * (i + 1), Tile.width, Tile.height, null);
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
                g.drawImage(getTileImg(tile), x + 2 * UNIT * i, y + (i > 18 ? HEIGHT / 2 : 0), Tile.width, Tile.height, null);
                i++;
            }
        }
    }

    public void loadImages() {
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
        } catch (IOException e) {
            System.out.println("IOException :");
            e.printStackTrace();
        }

    }

    public BufferedImage getTileImg(Tile tile) {
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

}
