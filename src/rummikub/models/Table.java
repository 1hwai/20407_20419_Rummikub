package rummikub.models;

import rummikub.models.player.Human;
import rummikub.models.player.Player;
import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.utils.AutoInsert;
import rummikub.models.utils.InValidTableException;
import rummikub.models.utils.BackUpManager;
import rummikub.models.tile.TileType;

import java.util.ArrayList;
import java.util.Queue;

public class Table implements BackUpManager {

    private final ArrayList<TileList> tableList = new ArrayList<>();
    private final ArrayList<TileList> tableListClone = new ArrayList<>();

    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private boolean hasChanged = false;

    private final TileSack sack = new TileSack();

    public static final Tile EMPTY = new Tile(1, TileType.EMPTY);

    public Table() {
        init();
        test();
    }

    private void init() {
        createEmptyTileList();
        //Prototype Player inserting code
        players.add(new Human("hawon"));
        players.add(new Human("donghyup"));
        players.forEach(p -> p.init(sack));
        currentPlayer = players.get(0);
        save();
        getCurrentPlayer().action(this);
    }

    private void test() {

    }

    public void next() throws InValidTableException {
        if (!validate()) {
            throw new InValidTableException();
        }

        if (currentPlayer.getDeck().isEmpty()) players.remove(currentPlayer);
        if (!hasChanged && sack.isExtractable() && players.contains(currentPlayer))
            currentPlayer.addToDeck(sack.extractTile());

        save();
        getCurrentPlayer().save();
        updateCurrentPlayer();
        setUnChanged();
        getCurrentPlayer().action(this);
    }

    @Override
    public void save() {
        tableListClone.clear();
        int i = 0;
        for (TileList tileList : tableList) {
            if (i == tableList.size() - 1) break;
            tableListClone.add(tileList.clone());
            i++;
        }

        currentPlayer.save();
    }

    @Override
    public void reset() {
        tableList.clear();
        int i = 0;
        for (TileList tileList : tableListClone) {
            if (i == tableListClone.size()) break;
            tableList.add(tileList.clone());
            i++;
        }

        createEmptyTileList();

        currentPlayer.reset();
    }

    public void setChanged() {
        hasChanged = true;
    }

    public void setUnChanged() {
        hasChanged = false;
    }

    public boolean validate() {
//        tableList.removeIf(ArrayList::isEmpty);

        int i = 0;
        for (TileList tileList : tableList) {
            if  (i == tableList.size() - 1) break;
            if (!tileList.validate()) return false;
            i++;
        }

        return true;
    }

    private void updateCurrentPlayer() {
        currentPlayer.initOnHand();
        int idx = (players.indexOf(currentPlayer) + 1) % players.size();
        currentPlayer = players.get(idx);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<TileList> getTableList() {
        return tableList;
    }

    public void insertTileList(TileList tileList, Queue<Tile> onHand) {
        if (onHand.isEmpty() || !tableList.contains(tileList)) return;
        tileList.remove(EMPTY);

        if (!getCurrentPlayer().isRegistered()) {
            TileList onHand0 = new TileList();
            for (Tile tile : onHand) onHand0.add(onHand0.size(), tile);
            if (onHand0.isSummable() && onHand0.sum() >= 30) {
                getCurrentPlayer().setRegistered();
            }
        }
        AutoInsert.autoInsert(tileList, onHand, getCurrentPlayer().useAutoSorting);

        if (tableList.indexOf(tileList) == tableList.size() - 1)
            createEmptyTileList();
    }

    private void createEmptyTileList() {
        if (!tableList.isEmpty()) {
            TileList tailTileList = tableList.get(tableList.size() - 1);
            if (tailTileList.isEmpty()) return;
        }

        TileList emptyTileList = new TileList();
        emptyTileList.add(EMPTY);
        tableList.add(emptyTileList);
    }

}
