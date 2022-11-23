package rummikub.models.utils;

public class InValidTableException extends Exception{

    public InValidTableException() {
        super("Invalid table statement.");
    }
}
