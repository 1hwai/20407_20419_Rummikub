package rummikub.common.utils;

public class IllegalNumberException extends Exception {
    public IllegalNumberException() {

    }

    public void printError() {
        System.out.println("Illegal State Exception : Invalid Number " + this);
    }
}
