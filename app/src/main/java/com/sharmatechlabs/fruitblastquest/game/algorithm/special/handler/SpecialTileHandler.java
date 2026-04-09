package com.sharmatechlabs.fruitblastquest.game.algorithm.special.handler;

import com.sharmatechlabs.fruitblastquest.game.layer.tile.Tile;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface SpecialTileHandler {

    void handleSpecialTile(Tile[][] tiles, Tile tile, int row, int col);

}
