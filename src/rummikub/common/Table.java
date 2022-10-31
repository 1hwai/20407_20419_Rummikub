package rummikub.common;

import rummikub.common.player.Human;
import rummikub.common.player.Player;
import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;

import java.util.ArrayList;

public class Table {
    public ArrayList<TileList> tableList = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;

    public Table() {
        TileList emptyTileList = new TileList();
        tableList.add(emptyTileList);

        //Prototype Player inserting code
        players.add(new Human("hawon"));
        players.add(new Human("donghyup"));

        currentPlayer = players.get(0);
    }

    public void update() {
        validate();
        updateCurrentPlayer();
    }

    private void validate() {

    }

    private void updateCurrentPlayer() {
        int idx = (players.indexOf(currentPlayer) + 1) % players.size();
        currentPlayer = players.get(idx);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void insertTile(Tile playerTile, int idx) {
        TileList tileList = tableList.get(idx);
        try {
            tileList.insertTile(playerTile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createEmptyTileList();
    }

    public void insertTileList(TileList playerTileList, int idx) {
        TileList tileList = tableList.get(idx);
        tileList.insertTileList(playerTileList);
        createEmptyTileList();
    }

    private void createEmptyTileList() {
        TileList tailTileList = tableList.get(tableList.size() - 1);
        if (tailTileList.list.isEmpty()) return;
        TileList emptyTileList = new TileList();
        tableList.add(emptyTileList);
    }

}
