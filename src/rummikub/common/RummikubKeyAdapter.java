package rummikub.common;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RummikubKeyAdapter extends KeyAdapter {

    public RummikubKeyAdapter() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keycode : " + e.getKeyCode());

    }
}