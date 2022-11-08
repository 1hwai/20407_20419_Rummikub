package rummikub.common.utils;

import java.awt.*;

public class Pointer {

    private int i = 0, j = 0;
    public boolean isDeckSide = true;

    public Pointer() {

    }

    public void swapSide() {
        isDeckSide = !isDeckSide;
    }

    public void move(Direction direction) {
        switch (direction) {
//            case UP -> point.move(point.x, point.y - 1);
//            case DOWN -> point.move(point.x, point.y + 1);
            case LEFT -> j--;
            case RIGHT -> j++;
        }
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}
