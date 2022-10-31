package rummikub.common.tile;

import rummikub.common.utils.TileColor;
import rummikub.common.utils.IllegalNumberException;

public class Tile {
    public int number;
    public TileColor color;

    public Tile(int number, TileColor color) throws IllegalNumberException {
        if (!isValidNumber(number)) throw new IllegalNumberException();

        this.number = number;
        this.color = color;
    }

    private boolean isValidNumber(int number) {
        return number < 14;
    }

}
