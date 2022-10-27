package rummikub.common.utils;

import rummikub.common.tile.Tile;
import rummikub.common.tile.TileSack;

/**
 * The class EmptySackException throws exception
 * when the {@link TileSack} is empty,
 * so there's no more {@link Tile} for a player to extract.
 */
public class EmptySackException extends Exception{
    public EmptySackException() {

    }

    public void printException() {
        System.out.println("Illegal State Exception : Sack is Empty, cannot extract the tile " + this);
    }
}
