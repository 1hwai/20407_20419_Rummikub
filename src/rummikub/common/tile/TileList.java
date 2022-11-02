package rummikub.common.tile;

import java.util.ArrayList;

public class TileList {
    public TileListType listType;
    private ArrayList<Tile> list = new ArrayList<>();

    public TileList() {

    }

    public TileList(TileListType listType) {
        this.listType = listType;
    }

    public ArrayList<Tile> getList() {
        return list;
    }

    public void insertTile(Tile tile) {
//        if (listType == TileListType.ASCENDING) {
//        }
        list.add(tile);
    }

    public void insertTileList(TileList tilelist) {
        this.list.addAll(tilelist.list);
    }

    public Tile extractTile(int idx) {
        Tile tile = list.get(idx);
        list.remove(idx);
        return tile;
    }

}
