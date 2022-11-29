package rummikub.models.tile;

public class Tile {

    public int number;
    //Joker.number should be 0.
    public TileType type;
    private TileList parent;

    public static final int WIDTH = 60, HEIGHT = 80;

    public Tile(int number, TileType type) {
        this.number = number;
        this.type = type;
    }

    public void setParent(TileList tileList) {
        if (hasParent()) {
            parent.remove(this);
        }

        parent = tileList;
    }

    private boolean hasParent() {
        return parent != null;
    }

    public boolean isParent(TileList tileList) {
        return parent == tileList;
    }

    public boolean isType(TileType type) {
        return this.type == type;
    }

    public boolean isJoker() {
        return number == 0;
    }

    public void print() {
        System.out.println(type + " " + number);
    }
}
