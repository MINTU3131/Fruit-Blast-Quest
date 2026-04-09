package com.sharmatechlabs.fruitblastquest.game.layer.tile.type;

import com.sharmatechlabs.fruitblastquest.algorithm.TileState;
import com.sharmatechlabs.fruitblastquest.game.layer.tile.Tile;
import com.sharmatechlabs.fruitblastquest.game.layer.tile.TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class DummyTile extends ObstacleTile {

    private Tile mTargetTile;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public DummyTile(TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture);
        setActive(false);
        setVisible(false);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public Tile getTargetTile() {
        return mTargetTile;
    }

    public void setTargetTile(Tile targetTile) {
        mTargetTile = targetTile;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public boolean isSwappable() {
        if (mIsObstacle) {
            return false;
        }
        return super.isSwappable();
    }

    @Override
    public void popTile() {
        if (mIsObstacle) {
            // We shift the popTile call to the target tile
            mTargetTile.popTile();
            return;
        }
        super.popTile();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void popDummyTile() {
        // Remove the dummy effect
        mTileState = TileState.MATCH;
        mIsObstacle = false;
        setActive(true);
        setVisible(true);
    }
    //========================================================

}
