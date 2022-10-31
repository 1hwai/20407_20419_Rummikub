package rummikub.common.tile;

import java.util.ArrayList;

public class TileList {
    public TileListType listType;
    public ArrayList<Tile> list = new ArrayList<>();

    public TileList() {

    }

    public TileList(TileListType listType) {
        this.listType = listType;
    }

    public void insertTile(Tile tile) {
//        if (listType == TileListType.ASCENDING) {
//        }
        list.add(tile);
    }

    public void insertTileList(TileList tilelist) {
        this.list.addAll(tilelist.list);
    }

}
