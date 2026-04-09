package com.sharmatechlabs.fruitblastquest.game.hint.finder;

import com.sharmatechlabs.fruitblastquest.game.layer.tile.Tile;

import java.util.List;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public interface HintFinder {

    List<Tile> findHint(Tile[][] tiles, int row, int col);

}
