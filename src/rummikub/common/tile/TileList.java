package rummikub.common.tile;

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
        tile.setBelong(this);
        list.add(tile);
    }

    public void insertTileList(TileList tilelist) {
        for (Tile tile : tilelist.list) {
            insertTile(tile);
        }
    }

    public Tile extractTile(int idx) {
        Tile tile = list.get(idx);
        tile.removeBelong();
        list.remove(idx);
        return tile;
    }

}
