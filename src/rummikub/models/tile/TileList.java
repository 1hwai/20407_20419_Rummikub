package rummikub.models.tile;

import rummikub.models.utils.IllegalTileListException;

import java.util.ArrayList;

public class TileList extends ArrayList<Tile> implements TileListValidator {

    public TileList() {
    }

    public boolean add(Tile tile) {
        super.add(tile);
        tile.setParent(this);

        return true;
    }

    public boolean validate() {
        return size() >= 3 && (isASC() || isSIB());
    }

    @Override
    public boolean isASC() {
        TileType type = TileType.WHITE;
        int number = 1;
        int i = 0;
        for (Tile tile : this) {
            if (!tile.isJoker()) {
                number = tile.number;
                type = tile.type;
                break;
            }
            i++;
        }
        if (number - i <= 0) return false;
        //if Joker's before 1 || if 2 Jokers' before 2;

        for (Tile tile : this) {
            if (!tile.isJoker()) {
                if (!tile.isType(type)) return false;
                if (tile.number != number) return false;
                number++;
            }
        }
        return true;
    }

    @Override
    public boolean isSIB() {
        ArrayList<TileType> types = new ArrayList<>();
        int number = 1;
        for (Tile tile : this) {
            if (!tile.isJoker()) {
                number = tile.number;
                break;
            }
        }

        for (Tile tile : this) {
            if (!tile.isJoker()) {
                if (tile.number != number || types.contains(tile.type)) return false;
                types.add(tile.type);
            }
        }
        return true;
    }

    public int sum() throws IllegalTileListException {
        if (size() < 3) throw new IllegalTileListException("TileList not Summable : TileList should have more than 3 tiles. ");

        int n = 1, i = 0;
        for (Tile tile : this) {
            if (!tile.isJoker()) {
                n = tile.number;
                break;
            }
            i++;
        }
        if (isASC())
            return size() * (2 * (n - i) + size() - 1) / 2;
            // sum of arithmetic sequences
            // 등차수열의 합
        else if (isSIB()) return size() * n;
        else throw new IllegalTileListException("TileList not Summable : tried to sum invalid TileList. ");

    }

    @Override
    public TileList clone() {
        TileList clone = new TileList();
        for (Tile tile : this) {
            clone.add(new Tile(tile.number, tile.type));
        }

        return clone;
    }

    public void print() {
        System.out.println(this + " {");
        for (Tile tile : this) {
            System.out.println("    " + tile.type + " " + tile.number);
        }
        System.out.println("}");
    }
}
