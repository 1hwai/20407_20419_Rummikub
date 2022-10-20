package rummikub;

import rummikub.common.Tile;
import rummikub.common.TileSack;

public class Rummikub {
    public static void main(String[] args) {
        TileSack sack = new TileSack();
        Tile tile = sack.extractTile();
        tile.printTile();

    }
}
