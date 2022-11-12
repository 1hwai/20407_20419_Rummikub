package rummikub.common.player;

import rummikub.common.Table;
import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.Movement;

import java.util.ArrayList;

public class Pointer implements PlayerHandler {

    public boolean isDeckSide = true;

    private final Table table;

    //Use tableListIdx instead of tileList
    //발적화 너무 많이 됨
    private TileList tileList;
    private int tableListIdx = 0;
    private Tile tile;

    public Pointer(Table table) {
        this.table = table;
        init();
    }

    public void init() {
        tileList = isDeckSide ? table.getCurrentPlayer().getDeck() : table.getTableList().get(0);
        tile = table.getCurrentPlayer().getDeck().getList().get(0);
    }

    public void swapSide() {
        isDeckSide = !isDeckSide;
        init();
        if (!isDeckSide){
            tile = tileList.getList().get(0);
        }
    }

    public void move(Movement movement) {
        if (!isAbleToMove()) return;
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
            ArrayList<TileList> tableList = table.getTableList();
            int listIdx = tableList.indexOf(tileList);
            ArrayList<Tile> list = tileList.getList();
            int idx = tileList.getList().indexOf(tile);
            switch (movement) {
                case LEFT -> tile = list.get(idx > 0 ? idx - 1 : list.size() - 1);
                case RIGHT -> tile = list.get((idx + 1) % list.size());
                case QUICK_LEFT -> {
                    tileList = tableList.get(listIdx > 0 ? listIdx - 1 : tableList.size() - 1);
                    tile = tileList.getList().get(0);
                }
                case QUICK_RIGHT -> {
                    tileList = tableList.get((listIdx + 1) % tableList.size());
                    tile = tileList.getList().get(0);
                }
            }
        }
    }

    private boolean isAbleToMove() {
        if (isDeckSide) {
            return !table.getCurrentPlayer().getDeck().getList().isEmpty();
        } else {
            return !table.getTableList().isEmpty();
        }
    }

    public Tile getTile() {
        return tile;
    }

    public boolean isTile(Tile tile) {
        return this.tile == tile;
    }

    @Override
    public void insert() {
        if (isDeckSide) return;
        table.setChanged();
        TileList onHand = table.getCurrentPlayer().getOnHand();
        table.insertTileList(onHand, table.getTableList().indexOf(tileList));
        onHand.getList().clear();
        table.validate();
    }

    @Override
    public void select() {
        if (getTile() == Table.EMPTY) return;
        table.getCurrentPlayer().setOnHand(getTile());
    }

    @Override
    public void cancel() {
        TileList onHand = table.getCurrentPlayer().getOnHand();
        if (!onHand.getList().isEmpty()) onHand.extractTile(onHand.getList().size() - 1);

    }

}
