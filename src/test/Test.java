package test;


import rummikub.common.TileSack;
import rummikub.common.tile.TileList;
import rummikub.common.utils.EmptySackException;

import java.util.Random;

public class Test {
    public static void main(String[] args) throws EmptySackException {

        TileSack sack = new TileSack();
        TileList tileList = new TileList();
        TileList tileList1;

        for (int i = 0; i < 5; i++)
            tileList.insertTile(sack.extractTile());
        tileList1 = tileList.clone();
        tileList1.insertTile(sack.extractTile());

        tileList.print();
        tileList1.print();

    }
}
