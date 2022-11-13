package rummikub.common.tile;

import jdk.jfr.Experimental;
import rummikub.common.Table;
import rummikub.common.utils.TileType;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class TileList implements TileListValidator {

    private final ArrayList<Tile> list = new ArrayList<>();

    public TileList() {

    }

    public ArrayList<Tile> getList() {
        return list;
    }

    public void insertTile(Tile tile) {
        if (tile.hasBelong()) {
            tile.getBelong().getList().remove(tile);
        }

        tile.setBelong(this);
        list.add(tile);
    }

    public void insertTileList(TileList tilelist) {
        tilelist.getList().remove(Table.EMPTY);
        for (Tile tile : tilelist.getList()) {
            insertTile(tile);
        }
    }

    public void removeTile(Tile tile) {
        if (!contains(tile)) return;
        list.remove(tile);
    }

    public Tile extractTile(int idx) {
        Tile tile = list.get(idx);
        list.remove(idx);
        return tile;
    }

    public boolean validate() {
        return list.size() >= 3 && (validateAsASC() || validateAsSIB());
    }

    public boolean validateAsASC() {
        TileType type = TileType.WHITE;
        int C = 1;
        for (Tile tile : getList())
            if (!tile.isJoker()) {
                C = tile.number;
                type = tile.type;
                break;
            }
        for (Tile tile : getList()) {
            if (tile.isJoker()) break;
            if (!tile.isType(type)) return false;
            if (tile.number != C) return false;
            C++;
        }
        return true;
    }

    public boolean validateAsSIB() {
        int number = 1;
        for (Tile tile : getList())
            if (!tile.isJoker()) {
                number = tile.number;
                break;
            }
        for (Tile tile : getList()) {
            if (tile.isJoker()) break;
            if (tile.number != number) return false;
        }
        return true;
    }

    public boolean contains(Tile tile) {
        return list.contains(tile);
    }

    @Override
    public TileList clone() {
        TileList clone = new TileList();
        clone.getList().remove(Table.EMPTY);
        for (Tile tile : getList()) {
            clone.getList().add(new Tile(tile.number, tile.type));
        }
        //Used ArrayList.add() since it's easier to manage Tile.belong than TileList.insertTile().

        return clone;
    }

    public void print() {
        System.out.println(this + " {");
        for (Tile tile : getList()) {
            System.out.println("    " + tile.type + " " + tile.number);
        }
        System.out.println("}");
    }
}
