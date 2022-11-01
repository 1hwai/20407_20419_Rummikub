package rummikub.common;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.TileColor;
import rummikub.common.utils.EmptySackException;

import java.util.Random;

public class TileSack {

    public TileList sack;

    public TileSack() {
        sack = new TileList();
        for (TileColor color : TileColor.values()) {
            for (int i = 1; i < 14; i++) {
                    Tile tile = new Tile(i, color);
                    sack.getList().add(tile);
                    sack.getList().add(tile);
            }
        }
        Tile blackJoker = new Tile(0, TileColor.WHITE);
        Tile redJoker = new Tile(0, TileColor.RED);
        sack.getList().add(blackJoker);
        sack.getList().add(redJoker);
    }

    public boolean isExtractable() {
        return !sack.getList().isEmpty();
    }

    public Tile extractTile() throws EmptySackException {
        if (!isExtractable()) throw new EmptySackException();

        Random random = new Random();
        final int idx = random.nextInt(sack.getList().size() - 1);
        Tile tile = sack.getList().get(idx);
        sack.getList().remove(idx);
        return tile;
    }

}
