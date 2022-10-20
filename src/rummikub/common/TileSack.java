package rummikub.common;

import rummikub.common.utils.Color;
import rummikub.common.utils.IllegalNumberException;

import java.util.Random;

public class TileSack {
    public TileList sack;

    public TileSack() {
        sack = new TileList();
        for (Color color : Color.values()) {
            for (int i = 1; i < 14; i++) {
                try {
                    Tile tile = new Tile(i, color);
                    sack.list.add(tile);
                } catch (IllegalNumberException e) {
                    e.printError();
                }
            }
        }
        try {
            Tile blackJoker = new Tile(0, Color.BLACK);
            Tile redJoker = new Tile(0, Color.RED);
            sack.list.add(blackJoker);
            sack.list.add(redJoker);
        } catch (IllegalNumberException e) {
            e.printError();
        }
    }

    public Tile extractTile() {
        int sackSize = sack.list.size();
        Random random = new Random();
        final int idx = random.nextInt(sackSize - 1);

        Tile tile = sack.list.get(idx);
        sack.list.remove(idx);
        return tile;
    }

    public void showSack() {
        for (Tile tile : sack.list) {
            tile.printTile();
        }
    }
}
