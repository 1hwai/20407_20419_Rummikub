package rummikub.models.player;

import rummikub.models.Table;
import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.tile.TileType;
import rummikub.models.utils.InValidTableException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class AI extends Player {

    private final Queue<TileList> newTileListAvailable = new LinkedList<>();

    public AI(String id) {
        super(id);
        useAutoSorting = true;
    }

    @Override
    public void action(Table table) {
        sortDeck();
        searchWithoutTable();
        sortDeck();
        searchWithoutTable();
        insertNew(table);
        if (!table.validate()) table.reset();
    }

    private void test(Table table) {
        System.out.println("Deck : {");
        getDeck().print();
        System.out.println("}");
        for (Tile tile : getDeck()) {
            TileList singleTileList = new TileList();
            singleTileList.add(tile);
            final Queue<TileList> availableLists = new LinkedList<>();
            getAvailableLists(table.getTableList(), availableLists, singleTileList);
        }

        for (TileList tileList : newTileListAvailable) {
            tileList.print();
        }
    }

    private void insertNew(Table table) {
        ArrayList<TileList> tableList = table.getTableList();
        while (!newTileListAvailable.isEmpty()) {
            TileList tileList1 = newTileListAvailable.poll();
            for (TileList tileList2 : newTileListAvailable) {
                if (hasIntersection(tileList1, tileList2)) {
                    if (tileList2.size() >= tileList1.size())
                        tileList1 = tileList2;
                    newTileListAvailable.remove(tileList2);
                }
            }
            Queue<Tile> onHand = new LinkedList<>(tileList1);
            table.insertTileList(tableList.get(tableList.size() - 1), onHand);
        }
    }

    private void searchWithoutTable() {
        for (int i = 3; i <= getDeck().size(); i++) {
            for (int j = 0; j < getDeck().size(); j+=i) {
                TileList tileList = new TileList();
                for (int k = j; k < j + i && k < getDeck().size(); k++) {
                    Tile tile = getDeck().get(k);
                    if (getDeck().size() > 6 && tile.isJoker()) break;
                    tileList.add(tile);
                }
                if (tileList.validate()) newTileListAvailable.add(tileList);
            }
        }
    }

    private void getAvailableLists(ArrayList<TileList> tableList, Queue<TileList> availableLists, TileList tileList0) {
        for (TileList tileList : tableList) {
            if (tileList0.size() == 1 && tileList0.get(0).isJoker()) break;
            if (tileList0.validate()) {
                availableLists.add(tileList0);
                return;
            }
//            if (tileList0.isSIB()) {
//                ArrayList<TileType> types = new ArrayList<>();
//                int number = 1;
//                for (Tile tile : tileList0) {
//                    if (!tile.isJoker()) {
//                        number = tile.number;
//                        break;
//                    }
//                }
//                for (Tile tile : tileList) {
//                    if (tile.isJoker() || (tile.number == number && !types.contains(tile.type))) {
//                        tileList0.add(tile);
//                        types.add(tile.type);
//                    }
//                }
//            }
            if (tileList.isASC()) {
                TileType type = TileType.WHITE;
                int number = 1;
                int i = 0;
                for (Tile tile : tileList0) {
                    if (!tile.isJoker()) {
                        number = tile.number;
                        type = tile.type;
                        break;
                    }
                    i++;
                }

                for (Tile tile : tileList) {
                    TileList tileList1 = new TileList();
                    tileList1.addAll(tileList0);
                    if (tile.type == type) {
                        if (tile.number + 1 == number) {
                            tileList1.add(0, tile);
                            ArrayList<TileList> newTableList = new ArrayList<>(tableList);
                            newTableList.remove(tileList);
                            getAvailableLists(newTableList, availableLists, tileList1);
                        } else if (tile.number == (tileList0.size() + number - i)) {
                            tileList1.add(tileList0.size() - 1, tile);

                            ArrayList<TileList> newTableList = new ArrayList<>(tableList);
                            newTableList.remove(tileList);
                            getAvailableLists(newTableList, availableLists, tileList1);
                        }
                    }
                }

            }
        }

        availableLists.removeIf(tileList -> !tileList.validate());

    }

    private boolean hasIntersection(TileList tileList1, TileList tileList2) {
        for (Tile tile : tileList1) {
            if (tileList2.contains(tile)) return false;
        }

        return true;
    }

    private void concatTileList(TileList tileList1, TileList tileList2) {
        tileList1.addAll(tileList2.clone());
    }

}
