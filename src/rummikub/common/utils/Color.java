package rummikub.common.utils;

public enum Color {
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    BLACK("\u001B[30m");

    final String colorCode;

    Color(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

    public static String reset() {
        return "\u001B[0m";
    }
}
