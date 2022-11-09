package rummikub.common.utils;

import rummikub.common.Table;
import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;

import java.util.ArrayList;

public class Pointer {

    public boolean isDeckSide = true;

    private final Table table;

    private TileList tileList;
    private Tile tile;

    public Pointer(Table table) {
        this.table = table;
        init();
    }

    public void init() {
        tileList = table.getTableList().get(0);
        tile = table.getCurrentPlayer().getDeck().getList().get(0);
    }

    public void swapSide() {
        isDeckSide = !isDeckSide;
    }

    public void move(Movement movement) {
        if (isDeckSide) {
            ArrayList<Tile> list = table.getCurrentPlayer().getDeck().getList();
            int idx = list.indexOf(tile);
            if (movement == Movement.LEFT) {
                tile = list.get(idx > 0 ? idx - 1 : list.size() - 1);
            } else if (movement == Movement.RIGHT) {
                tile = list.get((idx + 1) % list.size());
            }
        } else {
            // table에서 pointer 움직임, 위 if 문 참고할 것
            switch (movement) {
                case LEFT -> ;
                case RIGHT -> ;
                case QUICK_LEFT -> ;
                case QUICK_RIGHT -> ;
            }
        }
    }

}
