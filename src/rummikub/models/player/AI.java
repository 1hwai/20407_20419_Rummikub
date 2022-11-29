package rummikub.models.player;

import rummikub.models.Table;
import rummikub.models.tile.TileList;
import rummikub.models.utils.AutoInsert;

import java.util.ArrayList;


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
                if (!partialDeck.hasJoker() || getDeck().size() < 6) {
                    if (partialDeck.validate()) {
                        partialDeck.forEach(this::setOnHand);
                        table.insertTileList(tableList.get(tableList.size() - 1), getOnHand());
                    }
                    else if (isRegistered()) {
                        partialDeck.forEach(this::setOnHand);
                        for (TileList tileList : tableList) {
                            initOnHand();
                            AutoInsert.autoInsert(tileList, getOnHand(), useAutoSorting);
                            if (tileList.validate()) break;
                            else table.reset();
                        }
                    }
                    if (!table.validate()) table.reset();
                }
                if (table.validate()) table.save();
            }
        }
    }

}
