package rummikub.common.utils;

public enum TileColor {
    BLUE("B"),
    RED("R"),
    WHITE("W"),
    YELLOW("Y");

    final String colorCode;

    TileColor(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }

}
