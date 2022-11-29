package rummikub.models;

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

    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    public Player winner;

    private int turn = 1;

    public boolean hasFinished = false;

    private final TileSack sack = new TileSack();

    public static final Tile EMPTY = new Tile(1, TileType.EMPTY);

    public Table(ArrayList<Player> players) {
        init(players);
        test();
    }

    private void init(ArrayList<Player> players) {
        createEmptyTileList();
        this.players = players;
        this.players.forEach(p -> p.init(sack));
        currentPlayer = this.players.get(0);
        save();
        getCurrentPlayer().action(this);
    }

    private void test() {

    }

    public void next() throws InValidTableException {
        if (!validate()) {
            throw new InValidTableException();
        }

        if (getCurrentPlayer().getDeck().isEmpty()) {
            System.out.println(currentPlayer.getId());
            winner = currentPlayer;
            hasFinished = true;
            players.remove(currentPlayer);
        }
        if (sack.isExtractable() && players.contains(currentPlayer))
            currentPlayer.addToDeck(sack.extractTile());

        save();
        getCurrentPlayer().save();
        updateCurrentPlayer();
        setUnChanged();
        getCurrentPlayer().action(this);

        if (!sack.isExtractable()) {
            winner = getCurrentPlayer();
            for (Player player : players) {
                if (winner.getDeck().size() > player.getDeck().size()) {
                    winner = player;
                }
            }
            hasFinished = true;
        }
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

        getCurrentPlayer().reset();

        setUnChanged();
    }

    public void setChanged() {
    }

    public void setUnChanged() {
    }

    public boolean validate() {
        tableList.removeIf(ArrayList::isEmpty);

        int i = 0;
        for (TileList tileList : tableList) {
            if  (i == tableList.size() - 1) break;
            if (!tileList.validate()) return false;
            i++;
        }

        return true;
    }

    private void updateCurrentPlayer() {
        currentPlayer = players.get(turn++ % players.size());
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<TileList> getTableList() {
        return tableList;
    }

    public void insertTileList(TileList tileList, Queue<Tile> onHand) {
        if (onHand.isEmpty() || !tableList.contains(tileList)) return;
        if (!getCurrentPlayer().isRegistered()) {
            TileList onHand0 = new TileList();
            for (Tile tile : onHand) {
                if (!tile.isParent(getCurrentPlayer().getDeck())) return;
                onHand0.add(onHand0.size(), tile);
            }
            if (onHand0.isSummable() && onHand0.sum() >= 30) {
                getCurrentPlayer().setRegistered();
            } else return;
        }

        tileList.remove(EMPTY);
        AutoInsert.autoInsert(tileList, onHand, getCurrentPlayer().useAutoSorting);
        setChanged();
        if (tableList.indexOf(tileList) == tableList.size() - 1)
            createEmptyTileList();
    }

    private void createEmptyTileList() {
        if (!tableList.isEmpty()) {
            TileList tailTileList = tableList.get(tableList.size() - 1);
            if (tailTileList.isEmpty()) return;
        }

        TileList emptyTileList = new TileList();
        emptyTileList.addTile(EMPTY);
        tableList.add(emptyTileList);
    }

    public int getTurn() {
        return turn;
    }
}
