package rummikub.common.player;

public interface PointerAdapter {

    void insert();

    boolean isValidToInsert();

    void select();

    void cancel();
}
