package rummikub.models.player;

import rummikub.models.Table;
import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.utils.Movement;

import java.util.ArrayList;
import java.util.Queue;

public class Pointer implements PointerAdapter {

    public boolean isDeckSide = true;

    private final Table table;

    private TileList tileList;
    private Tile tile;

    public Pointer(Table table) {
        this.table = table;
        init();
    }

    public void init() {
        tileList = isDeckSide ? table.getCurrentPlayer().getDeck() : table.getTableList().get(0);
        tile = table.getCurrentPlayer().getDeck().get(0);
    }

    public void swapSide() {
        isDeckSide = !isDeckSide;
        init();
        if (!isDeckSide){
            tile = tileList.get(0);
        }
    }

    public void move(Movement movement) {
        if (!isAbleToMove()) return;
        if (isDeckSide) {
            TileList deck = table.getCurrentPlayer().getDeck();
            int idx = deck.indexOf(tile);
            if (movement == Movement.LEFT) {
                tile = deck.get(idx > 0 ? idx - 1 : deck.size() - 1);
            } else if (movement == Movement.RIGHT) {
                tile = deck.get((idx + 1) % deck.size());
            }
        } else {
            ArrayList<TileList> tableList = table.getTableList();
            int listIdx = tableList.indexOf(tileList);
            int idx = tileList.indexOf(tile);
            switch (movement) {
                case LEFT -> tile = tileList.get(idx > 0 ? idx - 1 : tileList.size() - 1);
                case RIGHT -> tile = tileList.get((idx + 1) % tileList.size());
                case QUICK_LEFT -> {
                    tileList = tableList.get(listIdx > 0 ? listIdx - 1 : tableList.size() - 1);
                    tile = tileList.get(0);
                }
                case QUICK_RIGHT -> {
                    tileList = tableList.get((listIdx + 1) % tableList.size());
                    tile = tileList.get(0);
                }
            }
        }
    }

    private boolean isAbleToMove() {
        if (isDeckSide) {
            return !table.getCurrentPlayer().getDeck().isEmpty();
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

        Queue<Tile> onHand = table.getCurrentPlayer().getOnHand();
        table.insertTileList(tileList, onHand);

        table.getTableList().removeIf(ArrayList::isEmpty);
    }

    @Override
    public void select() {
        if (getTile() == Table.EMPTY) return;
        table.getCurrentPlayer().setOnHand(getTile());
    }

    @Override
    public void cancel() {
        Queue<Tile> onHand = table.getCurrentPlayer().getOnHand();
        onHand.poll();
    }

}
