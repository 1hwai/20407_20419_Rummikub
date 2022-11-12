package rummikub.common.tile;

import rummikub.common.Table;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TileList {

    private final ArrayList<Tile> list = new ArrayList<>();

    public TileList() {

    }

    public ArrayList<Tile> getList() {
        return list;
    }

    public void insertTile(Tile tile) {
        if (tile.hasBelong()) {
            tile.getBelong().getList().remove(tile);
        }

        tile.setBelong(this);
        list.add(tile);
    }

    public void insertTileList(TileList tilelist) {
        tilelist.getList().remove(Table.EMPTY);
        for (Tile tile : tilelist.getList()) {
            insertTile(tile);
        }
    }

    public void removeTile(Tile tile) {
        if (!contains(tile)) return;
        list.remove(tile);
    }

    public Tile extractTile(int idx) {
        Tile tile = list.get(idx);
        list.remove(idx);
        return tile;
    }

    public boolean validate() {
        AtomicBoolean isTileListValid = new AtomicBoolean(false);
        isTileListValid.set(validateAsASC() || validateAsSIB());

        return isTileListValid.get();
    }

    private boolean validateAsASC() {
        return true;
    }

    private boolean validateAsSIB() {
        return true;
    }

    public boolean contains(Tile tile) {
        return list.contains(tile);
    }

}
