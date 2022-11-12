package rummikub.common.tile;

import rummikub.common.Table;

import java.util.ArrayList;

public class TileList {
    public TileListType listType;
    private final ArrayList<Tile> list = new ArrayList<>();

    public TileList() {

    }

    public TileList(TileListType listType) {
        this.listType = listType;
    }

    public ArrayList<Tile> getList() {
        return list;
    }

    public void insertTile(Tile tile) {
        if (tile.hasBelong()) {
            System.out.println(tile.type + " " + tile.number);
            System.out.println("I'm belonged to " + tile.getBelong());
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

    public Tile extractTile(Tile tile) {
        if (!contains(tile)) return null;
        tile.removeBelong();
        list.remove(tile);
        return tile;
    }

    public Tile extractTile(int idx) {
        Tile tile = list.get(idx);
        tile.removeBelong();
        list.remove(idx);
        return tile;
    }

    public boolean contains(Tile tile) {
        return list.contains(tile);
    }

}
