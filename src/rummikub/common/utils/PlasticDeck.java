package rummikub.common.utils;

import rummikub.common.panels.GamePanel;

import java.awt.*;

public class PlasticDeck extends Drawable {

    public PlasticDeck() {
        super(new Point((GamePanel.SCREEN_WIDTH - 60 * 18) / 2, GamePanel.SCREEN_HEIGHT - 100 * 3), 60 * 18, 4 * 18);
    }

}
