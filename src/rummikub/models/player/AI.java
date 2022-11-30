package rummikub.models.player;

import rummikub.models.Table;
import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.utils.AutoInsert;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class AI extends Player {

    public AI(String id) {
        super(id);
        useAutoSorting = true;
    }

    @Override
    public void action(Table table) {
        sortDeck();
        findAndInsert(table);
        sortDeck();
        findAndInsert(table);
        if (!table.validate()) table.reset();
    }

    private void findAndInsert(Table table) {
        ArrayList<TileList> tableList = table.getTableList();
        for (int i = getDeck().size(); i > 0; i--) {
            for (int j = 0; j + i <= getDeck().size(); j++) {
                initOnHand();
                TileList partialDeck = new TileList();
                for (int k = j; k < j + i; k++) {
                    partialDeck.add(getDeck().get(k));
                }
                if (!partialDeck.hasJoker() || getDeck().size() < 10) {
                    if (partialDeck.validate()) {
                        partialDeck.forEach(this::setOnHand);
                        table.insertTileList(tableList.get(tableList.size() - 1), getOnHand());
                    }
                    else if (isRegistered()) {
                        int idx = 1;
                        for (TileList tileList : tableList) {
                            if (idx++ == tableList.size()) break;
                            TileList tileListClone = tileList.clone();
                            Queue<Tile> onHandClone = new LinkedList<>(partialDeck.clone());
                            AutoInsert.autoInsert(tileListClone, onHandClone, true);

                            // 타일 리스트가 적합하지 않다면
                            // 조커가 해당 타일 리스트에 있을 경우만 적합한 위치로 변경해줌

//                            TileList prototype = new TileList();
//                            int _idx = 0;
//                            Queue<Tile> jokers = new LinkedList<>();
//
//                            for (Tile tile : tileListClone) if (tile.isJoker()) jokers.add(tile);
//
//                            for (Tile tile : tileListClone) {
//                                if (jokers.isEmpty()) break;
//                                prototype.add(tile);
//                                if (!(prototype.isASC() || prototype.isSIB())) prototype.add(_idx, jokers.poll());
//                                _idx++;
//                            }

                            // 위 코드를 일반화 시킴. 실제 실행 시간 얼마 안 걸림.
                            // 타일 리스트가 부적합하다면 다른 타일리스트에서 아무 타일이나 가져와서
                            // 유효성 판단. 유효하다면 가져옴
                            TileList prototype = new TileList();

                            for (Tile tile : tileListClone) {
                                prototype.add(tile);
                                if (!(prototype.isASC() || prototype.isSIB())) {
                                    for (TileList tileList1 : tableList) {
                                        for (Tile tile1 : tileList1) {
                                            prototype.add(tile1);
                                            if (!(prototype.isASC() || prototype.isSIB()))
                                                prototype.remove(tile1);
                                        }
                                    }
                                }
                            }
                            tileListClone = prototype;

//                            prototype.print();
//                            if (tileListClone.hasJoker()) {
//                                tileListClone = prototype;
//                            }
                            if (tileListClone.validate()) {
                                partialDeck.forEach(this::setOnHand);
                                table.insertTileList(tileList, getOnHand());
                            }

                            if (table.validate()) table.save();
                            else table.reset();
                        }
                    }
                    if (!table.validate()) table.reset();
                }
                if (table.validate()) table.save();
            }
        }
        initOnHand();
    }

}
