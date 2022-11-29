package rummikub.panels;

import rummikub.models.player.Player;

import java.awt.*;

public interface GamePanelDrawer {

    void drawIndicators(Graphics g);

    void drawAlert();

    void drawButtons(Graphics g);

    void drawTable(Graphics g);

    void drawPlayerDeck(Graphics g, Player player);

}
