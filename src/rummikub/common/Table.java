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

    public void update() {

    }

    private void validate() {

    }

    private void updateCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public void insertTile(Tile playerTile, int idx) {
        TileList tileList = tableList.get(idx);
        try {
            tileList.insertTile(playerTile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertTileList(TileList playerTileList, int idx) {
        TileList tileList = tableList.get(idx);
        tileList.insertTileList(playerTileList);
    }

    public void printTable() {
        int i = 1;
        for (TileList tileList : tableList) {
            tileList.print();
            if (i % 5 == 0) System.out.println();
            i++;
        }
    }
}
