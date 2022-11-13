package rummikub.common.tile;

import rummikub.common.utils.TileType;

public interface TileListValidator {
    boolean validate();

    boolean validateAsASC();

    boolean validateAsSIB();
}
