package rummikub.common;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.TileType;
import rummikub.common.utils.EmptySackException;

import java.util.Random;

public class TileSack {

    private final TileList sack = new TileList();

    public TileSack() {
        for (TileType type : TileType.values()) {
            if (type == TileType.EMPTY) break;
            for (int i = 1; i < 14; i++) {
                Tile tile0 = new Tile(i, type);
                Tile tile1 = new Tile(i, type);
                sack.insertTile(tile0);
                sack.insertTile(tile1);
            }
        }
        Tile blackJoker = new Tile(0, TileType.WHITE);
        Tile redJoker = new Tile(0, TileType.RED);
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
