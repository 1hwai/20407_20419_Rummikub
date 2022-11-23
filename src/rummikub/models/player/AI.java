package rummikub.models.player;

import rummikub.models.Table;
import rummikub.models.utils.InValidTableException;


public class AI extends Player {

    public AI(String id) {
        super(id);
    }

    @Override
    public void action(Table table) {
        try {

            table.next();
        } catch (InValidTableException e) {
            e.printStackTrace();
        }
    }
}
