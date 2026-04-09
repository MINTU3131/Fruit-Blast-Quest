package com.sharmatechlabs.fruitblastquest.game.layer.tile.type;

import com.sharmatechlabs.fruitblastquest.algorithm.TileState;
import com.sharmatechlabs.fruitblastquest.asset.Sounds;
import com.sharmatechlabs.fruitblastquest.asset.Textures;
import com.sharmatechlabs.fruitblastquest.game.effect.piece.StarfishPieceEffect;
import com.sharmatechlabs.fruitblastquest.game.layer.tile.FruitType;
import com.sharmatechlabs.fruitblastquest.game.layer.tile.TileSystem;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.texture.Texture;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class StarfishTile extends SolidTile {

    private final StarfishPieceEffect mStarfishPieceEffect;

    private boolean mIsStarfish = true;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public StarfishTile(TileSystem tileSystem, Engine engine, Texture texture) {
        super(tileSystem, engine, texture, FruitType.NONE);
        mStarfishPieceEffect = new StarfishPieceEffect(engine, Textures.STARFISH);
    }
    //========================================================

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public boolean isStarfish() {
        return mIsStarfish;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void popTile() {
        if (mIsStarfish) {
            return;
        }
        super.popTile();
    }

    @Override
    public void playTileEffect() {
        if (mIsStarfish) {
            playStarfishEffect();
            mIsStarfish = false;
            return;
        }
        super.playTileEffect();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void popStarfishTile() {
        // Important to not reuse popTile() or matchTile()
        mTileState = TileState.MATCH;
    }

    private void playStarfishEffect() {
        mStarfishPieceEffect.activate(getCenterX(), getCenterY());
        Sounds.COLLECT_STARFISH.play();
    }
    //========================================================

}
