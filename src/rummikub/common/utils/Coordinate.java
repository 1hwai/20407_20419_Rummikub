package rummikub.common.utils;

public class Coordinate {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void xpp() {
        setX(getX() + 1);
    }

    public void xmm() {
        setX(getX() - 1);
    }

    public void ypp() {
        setY(getY() + 1);
    }

    public void ymm() {
        setY(getY() - 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
