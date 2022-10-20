package rummikub.common;

import rummikub.common.player.Player;

import java.util.ArrayList;

public class Table {
    public ArrayList<TileList> tableList = new ArrayList<>();
    public ArrayList<Player> players = new ArrayList<>();
    public Player currentPlayer;

    public Table() {
        TileList emptyTileList = new TileList();
        tableList.add(emptyTileList);
    }

    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void insertTile(Tile tile, int idx) {
        TileList tileList = tableList.get(idx);
        try {
            tileList.insertTile(tile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTileList(TileList tileList, int idx) {

    }
}
