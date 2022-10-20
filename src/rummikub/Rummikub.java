package rummikub;

import rummikub.common.Table;
import rummikub.common.Tile;
import rummikub.common.TileSack;

public class Rummikub {
    public static void main(String[] args) {
        TileSack sack = new TileSack();
        Table table = new Table();

        for (int i = 0; i < 10; i++) {
            Tile tile = sack.extractTile();

        }

        table.printTable();

    }
}
