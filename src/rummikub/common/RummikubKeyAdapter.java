package rummikub.common;

import rummikub.common.panels.GamePanel;
import rummikub.common.utils.Coordinate;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RummikubKeyAdapter extends KeyAdapter {

    public RummikubKeyAdapter() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keycode : " + e.getKeyCode());
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_A:
                GamePanel.selector.xmm();
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_D:
                GamePanel.selector.xpp();
                break;
        }
    }
}