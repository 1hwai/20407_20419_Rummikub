package rummikub;

import rummikub.common.Table;
import rummikub.common.tile.TileList;
import rummikub.common.tile.TileSack;

public class Rummikub {
    public static void main(String[] args) throws Exception {

        TileSack sack = new TileSack();
        Table table = new Table();
        TileList tileList1 = new TileList();
        TileList tileList2 = new TileList();
        TileList tileList3 = new TileList();
        TileList tileList4 = new TileList();
        for (int i = 0; i < 8; i++) {
            tileList1.insertTile(sack.extractTile());
            tileList2.insertTile(sack.extractTile());
            tileList3.insertTile(sack.extractTile());
            tileList4.insertTile(sack.extractTile());
        }


        table.insertTileList(tileList1, 0);
        table.insertTileList(tileList3, 1);
        table.insertTileList(tileList4, 2);

        table.printTable();


    }
}
