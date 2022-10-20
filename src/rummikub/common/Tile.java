package rummikub.common;

import rummikub.common.utils.Color;
import rummikub.common.utils.IllegalNumberException;

public class Tile {
    public int number;
    public Color color;

    public Tile(int number, Color color) throws IllegalNumberException {
        if (!isValidNumber(number)) throw new IllegalNumberException();

        this.number = number;
        this.color = color;
    }

    private boolean isValidNumber(int number) {
        return number < 14;
    }

    public void printTile() {
        System.out.println(color.getColorCode() + " " + number + Color.reset());
    }
}
