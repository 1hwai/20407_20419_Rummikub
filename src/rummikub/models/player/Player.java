package rummikub.models.player;

import rummikub.models.Table;
import rummikub.models.TileSack;
import rummikub.models.tile.Tile;
import rummikub.models.tile.TileList;
import rummikub.models.utils.BackUpManager;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Player implements BackUpManager {

    private TileList deck = new TileList();
    private TileList deckClone = new TileList();

    private final Queue<Tile> onHand = new LinkedList<>();
    private boolean isRegistered = false;

    public boolean useAutoSorting = false;

    private final String id;

    public Player(String id) {
        this.id = id;
    }

    public void init(TileSack sack) {
        for (int i = 0; i < 14; i++) {
            deck.add(sack.extractTile());
        }
        save();
    }

    @Override
    public void save() {
        deckClone.clear();
        deckClone = deck.clone();
    }

    @Override
    public void reset() {
        deck.clear();
        deck = deckClone.clone();
    }

    public void action(Table table) {
    }

    public TileList getDeck() {
        return deck;
    }

    public Queue<Tile> getOnHand() {
        return onHand;
    }

    public String getId() {
        return id;
    }

    public void addToDeck(Tile tile) {
        deck.add(tile);
    }

    public void initOnHand() {
        onHand.clear();
    }

    //toggle
    public void setOnHand(Tile tile) {
        if (onHand.contains(tile)) onHand.remove(tile);
        else onHand.add(tile);
    }

    public void setUseAutoSorting() {
        useAutoSorting = !useAutoSorting;
    }

    public void reduceOnHand() {
        onHand.remove();
    }

    public void setRegistered() {
        isRegistered = true;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

}
