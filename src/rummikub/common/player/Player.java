package rummikub.common.player;

import rummikub.common.TileList;

public abstract class Player {
    TileList deck = new TileList();
    TileList onHand = new TileList();

    public Player() {

    }

    public void setOnHand(TileList tileList) {
        onHand = tileList;
    }


}
