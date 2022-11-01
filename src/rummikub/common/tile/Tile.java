package rummikub.common.tile;

import rummikub.common.utils.TileColor;

public class Tile {
    public int number;
    public TileColor color;

    public static final int width = 60, height = 80;

    public Tile(int number, TileColor color) {
        this.number = number;
        this.color = color;
    }

    private boolean isValidNumber(int number) {
        return number < 14;
    }

}
