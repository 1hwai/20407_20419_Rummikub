package rummikub.common;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.TileColor;
import rummikub.common.utils.EmptySackException;
import rummikub.common.utils.IllegalNumberException;

import java.util.Random;

public class TileSack {

    public TileList sack;

    public TileSack() throws IllegalNumberException {
        sack = new TileList();
        for (TileColor color : TileColor.values()) {
            for (int i = 1; i < 14; i++) {
                    Tile tile = new Tile(i, color);
                    sack.list.add(tile);
                    sack.list.add(tile);
            }
        }
        Tile blackJoker = new Tile(0, TileColor.WHITE);
        Tile redJoker = new Tile(0, TileColor.RED);
        sack.list.add(blackJoker);
        sack.list.add(redJoker);
    }

    public boolean isExtractable() {
        return !sack.list.isEmpty();
    }

    public Tile extractTile() throws EmptySackException {
        if (!isExtractable()) throw new EmptySackException();

        Random random = new Random();
        final int idx = random.nextInt(sack.list.size() - 1);
        Tile tile = sack.list.get(idx);
        sack.list.remove(idx);
        return tile;
    }

}
