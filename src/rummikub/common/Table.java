package rummikub.common;

import rummikub.common.player.Human;
import rummikub.common.player.Player;
import rummikub.common.tile.Tile;
import rummikub.common.tile.TileList;
import rummikub.common.utils.EmptySackException;

import java.util.ArrayList;

public class Table {
    private final ArrayList<TileList> tableList = new ArrayList<>();
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private boolean hasChanged = false;

    private final TileSack sack = new TileSack();

    public Table() {
        TileList emptyTileList = new TileList();
        tableList.add(emptyTileList);

        //Prototype Player inserting code
        players.add(new Human("hawon"));
        players.add(new Human("donghyup"));

        currentPlayer = players.get(0);

        test();
    }

    private void test() {
//        try {

            //test case 1
//            for (int i = 0; i < 8; i++) {
//                TileList list = new TileList();
//                for (int j = 0; j < 13; j++) {
//                    list.insertTile(sack.extractTile());
//                }
//                insertTileList(list, i);
//            }

            //test case 2
//            for (int i = 0; i < 106; i++) {
//                insertTile(sack.extractTile(), i);
//            }

//        } catch (EmptySackException e) {
//            e.printException();
//        }
    }

    public void next() {
        validate();
        try {
            if (!hasChanged) currentPlayer.insertTileToDeck(sack.extractTile());
        } catch (EmptySackException e) {
            hasChanged = true;
        }

        updateCurrentPlayer();
        hasChanged = false;
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

    public ArrayList<TileList> getTableList() {
        return tableList;
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
        if (tailTileList.getList().isEmpty()) return;
        TileList emptyTileList = new TileList();
        tableList.add(emptyTileList);
    }

}
