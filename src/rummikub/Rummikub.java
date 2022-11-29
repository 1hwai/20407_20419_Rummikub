package rummikub;

import rummikub.frames.GameFrame;
import rummikub.frames.MenuFrame;

import java.awt.*;

public class Rummikub {

    public static void main(String[] args) {
        EventQueue.invokeLater(MenuFrame::new);
    }

}
