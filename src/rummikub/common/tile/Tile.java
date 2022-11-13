package rummikub.common.tile;

import rummikub.common.utils.TileType;

public class Tile {

    public int number;
    //Joker.number should be 0.
    public TileType type;
    private TileList belong;

    public static final int width = 60, height = 80;

    public Tile(int number, TileType type) {
        this.number = number;
        this.type = type;
    }

    public void setBelong(TileList belong) {
        this.belong = belong;
    }

    public void removeBelong() {
        belong = null;
    }

    public boolean hasBelong() {
        return belong != null;
    }

    public TileList getBelong() {
        return belong;
    }

    public boolean isType(TileType type) {
        return this.type == type;
    }

    public boolean isJoker() {
        return number == 0;
    }

    private boolean isValidNumber(int number) {
        return -1 <= number && number <= 13;
    }

}
