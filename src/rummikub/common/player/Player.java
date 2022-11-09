package rummikub.common.player;

import rummikub.common.TileSack;
import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.EmptySackException;

public abstract class Player {

    private final TileList deck = new TileList();
    private TileList onHand = new TileList();

    private final String id;

    public Player(String id) {
        this.id = id;
    }

    public void init(TileSack sack) {
        try {
            for (int i = 0; i < 14; i++) {
                deck.insertTile(sack.extractTile());
            }
        } catch (EmptySackException e) {
            e.printStackTrace();
        }
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

    public void setOnHand(Tile tile) {
        // TODO: 손에 있으면 지우고 없으면 추가
        if (onHand.getList().contains(tile))
            onHand.extractTile(onHand.getList().indexOf(tile));
        else
            onHand.insertTile(tile);
    }

}
