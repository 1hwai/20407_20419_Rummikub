package rummikub.common;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.TileColor;
import rummikub.common.utils.EmptySackException;

import java.util.Random;

public class TileSack {

    private final TileList sack = new TileList();

    public TileSack() {
        for (TileColor color : TileColor.values()) {
            for (int i = 1; i < 14; i++) {
                    Tile tile = new Tile(i, color);
                    sack.insertTile(tile);
                    sack.insertTile(tile);
            }
        }
        Tile blackJoker = new Tile(0, TileColor.WHITE);
        Tile redJoker = new Tile(0, TileColor.RED);
        sack.insertTile(blackJoker);
        sack.insertTile(redJoker);
    }

    public Tile extractTile() throws EmptySackException {
        if (!isExtractable()) throw new EmptySackException();

        Random random = new Random();
        int size = sack.getList().size();
        final int idx = random.nextInt(size);

        return sack.extractTile(idx);
    }

    public boolean isExtractable() {
        return !sack.getList().isEmpty();
    }

}
