package rummikub.common.player;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;

public abstract class Player {
    private final TileList deck = new TileList();
    private TileList onHand = new TileList();

    public Player() {

    }

    public void insertTileToDeck(Tile tile) {
        deck.insertTile(tile);
    }

    public void printPlayerDeck() {
        deck.print();
    }

    public void setOnHand(TileList tileList) {
        onHand = tileList;
    }


}
