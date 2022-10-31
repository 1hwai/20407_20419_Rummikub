package rummikub.common.player;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.IllegalNumberException;
import rummikub.common.utils.TileColor;

import java.awt.*;

public abstract class Player {

    private final TileList deck = new TileList();
    private TileList onHand = new TileList();

    private final String id;

    public Player(String id) {
        this.id = id;
    }

    public TileList getDeck() {
        return deck;
    }

    public TileList getOnHand() {
        return onHand;
    }

    public String getId() {
        return id;
    }

    public void insertTileToDeck(Tile tile) {
        deck.insertTile(tile);
    }

    public void setOnHand(TileList tileList) {
        onHand = tileList;
    }


}
