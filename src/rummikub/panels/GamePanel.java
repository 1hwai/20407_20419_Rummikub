package rummikub.panels;

import rummikub.models.Table;
import rummikub.models.player.Player;
import rummikub.models.player.Pointer;
import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.tile.TileType;
import rummikub.models.utils.InValidTableException;
import rummikub.models.utils.Movement;
import rummikub.panels.components.PlasticDeck;
import rummikub.panels.components.buttons.NextTurnButton;
import rummikub.panels.components.buttons.ResetButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements GamePanelDrawer, ActionListener {

    public static final int SCREEN_WIDTH = 1800;
    public static final int SCREEN_HEIGHT = 900;

    private static final int FPS = 60;
    private static final int DELAY = 1000 / FPS;

    public static final int UNIT = 30;

    public static final Font TIMES_NEW_ROMAN_TITLE = new Font("Times new Roman", Font.PLAIN, 30);
    public static final Font TIMES_NEW_ROMAN_MID = new Font("Times new Roman", Font.PLAIN, 20);
    private static final Color ON_HAND_COLOR = new Color(82, 155, 218, 122);

    public Boolean running;

    private final ArrayList<BufferedImage> tileImages = new ArrayList<>();
    private BufferedImage pointerImg = null;
    private final PlasticDeck plasticDeck = new PlasticDeck();
    private NextTurnButton nextTurnBtn;
    private ResetButton resetBtn;

    public final Table table;
    private final Pointer pointer;

    public GamePanel(ArrayList<Player> players) {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new RummikubKeyAdapter());

        Timer timer = new Timer(DELAY, this);
        timer.start();
        running = true;

        table = new Table(players);
        pointer = new Pointer(table);
        initButtons();
        loadImages();
        addMouseListener(new RummikubMouseAdapter());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        drawIndicators(g);
        drawTable(g);
    }

    @Override
    public void drawIndicators(Graphics g) {
        g.setFont(TIMES_NEW_ROMAN_TITLE);
        g.setColor(Color.white);
        g.drawString("Current Player : " + table.getCurrentPlayer().getId(), UNIT, UNIT);
        g.setFont(TIMES_NEW_ROMAN_MID);
        g.drawString("AutoSorting : " + (table.getCurrentPlayer().useAutoSorting ? "ON" : "OFF"), UNIT, 2 * UNIT);

        drawButtons(g);
    }

    @Override
    public void drawAlert() {
        JOptionPane.showMessageDialog(null, "InValid Tile Insertion","WARNING", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void drawButtons(Graphics g) {
        nextTurnBtn.draw(g);
        resetBtn.draw(g);
    }

    @Override
    public void drawTable(Graphics g) {
        final int TABLE_WIDTH = GamePanel.SCREEN_WIDTH - UNIT * 2;
        int i = 0;
        int rowWidth = 0;
        for (TileList tileList : table.getTableList()) {
            int j = 0;
            int listWidth = 2 * UNIT * tileList.size();
            if (rowWidth + listWidth > TABLE_WIDTH) {
                rowWidth = 0;
                i++;
            }
            for (Tile tile : tileList) {
                int x = rowWidth + UNIT + 2 * UNIT * j;
                int y = 3 * UNIT * (i + 1);
                g.drawImage(getTileImg(tile), x, y, Tile.WIDTH, Tile.HEIGHT, null);
                if (table.getCurrentPlayer().getOnHand().contains(tile)) {
                    g.setColor(ON_HAND_COLOR);
                    g.fillRect(x, y, Tile.WIDTH, Tile.HEIGHT);
                }
                if (pointer.isTile(tile)) {
                    g.drawImage(pointerImg, x, y, Tile.WIDTH, Tile.HEIGHT, null);
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
        int x = plasticDeck.position.x;
        int y = plasticDeck.position.y + 30;

        plasticDeck.draw(g);

        TileList deck = player.getDeck();
        int i = 0;
        for (Tile tile : deck) {
            int x1 = x + 2 * UNIT * (i % 18);
            int y1 = y + WIDTH/4 * (i / 18) / 3;
            g.drawImage(getTileImg(tile), x1, y1, Tile.WIDTH, Tile.HEIGHT, null);
            if (table.getCurrentPlayer().getOnHand().contains(tile)) {
                g.setColor(ON_HAND_COLOR);
                g.fillRect(x1, y1, Tile.WIDTH, Tile.HEIGHT);
            }
            if (pointer.isTile(tile)) {
                g.setColor(Color.BLACK);
                g.drawImage(pointerImg, x1, y1, Tile.WIDTH, Tile.HEIGHT, null);
            }
            i++;
        }
        if (deck.isEmpty()) {
            g.drawImage(pointerImg, x, y, Tile.WIDTH, Tile.HEIGHT, null);
        }
    }

    private void initButtons() {
        nextTurnBtn = new NextTurnButton(new Point(SCREEN_WIDTH - 200, SCREEN_HEIGHT - 200));
        resetBtn = new ResetButton(new Point(100, SCREEN_HEIGHT - 200));
    }

    private void loadImages() {
        try {
            final int TILE_IMAGE_SIZE = 55;
            final String SOURCE = "/resources/tiles/";
            for (TileType type : TileType.values()) {
                if (type == TileType.EMPTY) break;
                String typeCode = type.getTypeCode();
                for (int i = 1; i < 14; i++) {
                    tileImages.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(SOURCE + typeCode + i + ".png"))));
                    System.out.println(SOURCE + typeCode + i + ".png" + " has been Successfully loaded");
                }
            }
            tileImages.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(SOURCE + "RED_JOKER.png"))));
            tileImages.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(SOURCE + "WHITE_JOKER.png"))));
            System.out.println(SOURCE + "RED_JOKER.png" + " has been Successfully loaded");
            System.out.println(SOURCE + "WHITE_JOKER.png" + " has been Successfully loaded");
            tileImages.add(ImageIO.read(Objects.requireNonNull(getClass().getResource(SOURCE + "EMPTY.png"))));
            System.out.println(SOURCE + "EMPTY.png" + " has been Successfully loaded");

            System.out.println("**********************");
            if (tileImages.size() < TILE_IMAGE_SIZE) System.out.println("Error : TileImage not loaded");
            else if (tileImages.size() > TILE_IMAGE_SIZE) System.out.println("Warning : TileImages have been Overloaded");
            else System.out.println("All Tiles have been Successfully loaded");
            System.out.println("**********************");

            plasticDeck.setImage("/resources/utils/PLASTIC_DECK.png");
            System.out.println("/resources/utils/PLASTIC_DECK.png has been Successfully loaded");
            pointerImg = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/utils/POINTER.png")));
            System.out.println("/resources/utils/POINTER.png has been Successfully loaded");

            nextTurnBtn.setImage("/resources/indicators/NEXT_TURN_BTN.png");
            System.out.println("/resources/indicators/NEXT_TURN_BTN.png has been Successfully loaded");
            resetBtn.setImage("/resources/indicators/RESET_BTN.png");
            System.out.println("/resources/indicators/RESET_BTN.png has been Successfully loaded");

        } catch (IOException e) {
            System.out.println("IOException :");
            e.printStackTrace();
        }

    }

    private BufferedImage getTileImg(Tile tile) {
        if (tile.type == TileType.EMPTY) return tileImages.get(tileImages.size() - 1);
        if (tile.number == 0) {
            if (tile.type == TileType.WHITE) return tileImages.get(tileImages.size() - 2);
            else return tileImages.get(tileImages.size() - 3);
        }

        int i = 0;
        for (TileType type : TileType.values()) {
            if (type == tile.type) break;
            i++;
        }

        return tileImages.get(13 * i + tile.number - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private final class RummikubMouseAdapter extends MouseAdapter {

        public RummikubMouseAdapter() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Point mp = new Point(e.getX(), e.getY());

            nextTurnBtn.ifClicked(mp, () -> {
                try {
                    table.next();
                } catch (InValidTableException ex) {
                    ex.printStackTrace();
                    drawAlert();
                }
                pointer.init();
                pointer.isDeckSide = true;
                return null;
            });
            resetBtn.ifClicked(mp, () -> {
                table.reset();
                return null;
            });
        }
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
                case KeyEvent.VK_S -> table.getCurrentPlayer().setUseAutoSorting();

                //?????? ?????????!!
                case KeyEvent.VK_X -> table.getCurrentPlayer().sortDeck();

                case KeyEvent.VK_BACK_SPACE -> pointer.cancel();
                case KeyEvent.VK_SHIFT -> pointer.select();
                case KeyEvent.VK_SPACE -> pointer.swapSide();
                case KeyEvent.VK_ENTER -> pointer.insert();
            }
        }
    }
}
