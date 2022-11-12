package rummikub.common.utils;

public enum TileType {
    BLUE("B"),
    RED("R"),
    WHITE("W"),
    YELLOW("Y"),

    EMPTY("Empty");

    final String typeCode;

    TileType(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

}
