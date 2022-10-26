package rummikub.common.utils;

public class EmptySackException extends Exception{
    public EmptySackException() {

    }

    public void printException() {
        System.out.println("Illegal State Exception : Sack is Empty, cannot extract the tile " + this);
    }
}
