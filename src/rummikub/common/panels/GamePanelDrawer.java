package rummikub.common.panels;

import rummikub.common.player.Player;

import java.awt.*;

public interface GamePanelDrawer {

    void drawIndicators(Graphics g);

    void drawTable(Graphics g);

    void drawPlayerDeck(Graphics g, Player player);

}
