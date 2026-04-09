package com.sharmatechlabs.fruitblastquest.game.tutorial;

import com.sharmatechlabs.fruitblastquest.asset.Colors;
import com.sharmatechlabs.fruitblastquest.asset.Textures;
import com.sharmatechlabs.fruitblastquest.game.GameWorld;
import com.sharmatechlabs.fruitblastquest.game.effect.tutorial.TutorialFingerEffect;
import com.sharmatechlabs.fruitblastquest.game.effect.tutorial.TutorialHintEffectSystem;
import com.sharmatechlabs.fruitblastquest.game.effect.tutorial.TutorialShadowEffect;
import com.sharmatechlabs.fruitblastquest.level.Level;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.ui.GameActivity;

/**
 * Created by Oscar Liang on 2022/02/23
 */

public class CandyTutorial implements Tutorial {

    private final TutorialShadowEffect mShadowBg;
    private final TutorialHintEffectSystem mHintEffect;
    private final TutorialFingerEffect mFingerEffect;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public CandyTutorial(Engine engine) {
        mShadowBg = new TutorialShadowEffect(engine, Colors.BLACK_80);
        mHintEffect = new TutorialHintEffectSystem(engine);
        mFingerEffect = new TutorialFingerEffect(engine, Textures.TUTORIAL_FINGER);
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void show(GameActivity activity) {
        mShadowBg.addToGame();
        mHintEffect.activate(Level.LEVEL_DATA.getTutorialHint().toCharArray());
        int marginX = (GameWorld.WORLD_WIDTH - Level.LEVEL_DATA.getColumn() * 300) / 2;
        int marginY = (GameWorld.WORLD_HEIGHT - Level.LEVEL_DATA.getRow() * 300) / 2;
        mFingerEffect.activate(marginX + 600, marginX + 900, marginY + 1300, marginY + 1300);
    }
    //========================================================

}

