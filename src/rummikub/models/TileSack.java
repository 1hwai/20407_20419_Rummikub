package rummikub.models;

import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.tile.TileType;

import java.util.Random;

public class TileSack {

    private final TileList sack = new TileList();

    public TileSack() {
        for (TileType type : TileType.values()) {
            if (type == TileType.EMPTY) break;
            for (int i = 1; i < 14; i++) {
                Tile tile0 = new Tile(i, type);
                Tile tile1 = new Tile(i, type);
                sack.addTile(tile0);
                sack.addTile(tile1);
            }
        }
        Tile blackJoker = new Tile(0, TileType.WHITE);
        Tile redJoker = new Tile(0, TileType.RED);
        sack.addTile(blackJoker);
        sack.addTile(redJoker);
    }

    public Tile extractTile() {
        if (!isExtractable()) return null;
        Random random = new Random();
        final int idx = random.nextInt(sack.size());

        Tile tile = sack.get(idx);
        sack.remove(tile);
        return tile;
    }

    public boolean isExtractable() {
        return !sack.isEmpty();
    }

}
