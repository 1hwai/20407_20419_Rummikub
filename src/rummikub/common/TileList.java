package rummikub.common;

import java.util.ArrayList;

public class TileList {
    public ArrayList<Tile> list = new ArrayList<>();

    public void insertList(Tile tile) throws Exception {
        if (isValidTile(tile)) {
            list.add(tile);
        } else {

        }
    }

    public boolean isValidTile(Tile tile) {
        return false;
    }
}
