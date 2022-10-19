package rummikub;

import rummikub.common.Tile;
import rummikub.common.utils.Color;

public class Rummikub {
    public static void main(String[] args) {
        Tile tileBlueTen = new Tile(10, Color.BLUE);
        tileBlueTen.printTile();
        Tile tileRedTen = new Tile(10, Color.RED);
        tileRedTen.printTile();
    }
}
