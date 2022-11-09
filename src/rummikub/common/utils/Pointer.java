package rummikub.common.utils;

public class Pointer {

    private int i = 0, j = 0;
    public boolean isDeckSide = true;

    public Pointer() {

    }

    public void swapSide() {
        isDeckSide = !isDeckSide;
    }

    public void move(Movement direction) {
        switch (direction) {
            case QUICK_LEFT -> i--;
            case LEFT -> j--;
            case QUICK_RIGHT -> i++;
            case RIGHT -> j++;
        }
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void printPosition() {
        System.out.println("fdsffsdf");
        System.out.println(i + ", " + j);
    }

    public boolean isOn(int i, int j) {
        return i == getI() && j == getJ();
    }
}
