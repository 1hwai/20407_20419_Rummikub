package rummikub.common;

import rummikub.common.utils.Color;

public class Tile {
    public int number;
    public Color color;

    public Tile(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public void printTile() {
        System.out.println(color.getColorCode() + " " + number + Color.reset());
    }
}
