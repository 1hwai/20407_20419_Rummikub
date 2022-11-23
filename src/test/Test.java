package test;


import rummikub.models.TileSack;
import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.tile.TileType;
import rummikub.models.utils.AutoInsert;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        TileSack sack = new TileSack();
        TileList deck = new TileList();
        for (int i = 0; i < 14; i++) {
            deck.add(sack.extractTile());
        }

        deck.print();
        TileList newDeck = new TileList();

        Map<TileType, TileList> map = new HashMap<>();
        for (TileType type : TileType.values()) map.put(type, new TileList());

        for (Tile tile : deck) {
            if (tile.isJoker()) {
                newDeck.add(tile);
                break;
            }
            map.get(tile.type).add(map.get(tile.type).size(), tile);
        }
        for (TileType type : TileType.values()) {
            if (type == TileType.EMPTY) break;
            AutoInsert.quickSort(map.get(type), 0, map.get(type).size() - 1);
            newDeck.addAll(map.get(type));
        }

        newDeck.print();
    }
}