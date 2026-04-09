package com.sharmatechlabs.fruitblastquest.game;

import com.sharmatechlabs.fruitblastquest.MainActivity;
import com.sharmatechlabs.fruitblastquest.game.algorithm.Algorithm;
import com.sharmatechlabs.fruitblastquest.game.algorithm.BonusTimeAlgorithm;
import com.sharmatechlabs.fruitblastquest.game.algorithm.RegularTimeAlgorithm;
import com.sharmatechlabs.fruitblastquest.game.algorithm.layer.LayerHandlerManager;
import com.sharmatechlabs.fruitblastquest.game.algorithm.target.TargetHandlerManager;
import com.sharmatechlabs.fruitblastquest.game.counter.BoosterCounter;
import com.sharmatechlabs.fruitblastquest.game.counter.ComboCounter;
import com.sharmatechlabs.fruitblastquest.game.counter.MoveCounter;
import com.sharmatechlabs.fruitblastquest.game.counter.ScoreCounter;
import com.sharmatechlabs.fruitblastquest.game.counter.StarCounter;
import com.sharmatechlabs.fruitblastquest.game.counter.TargetCounter;
import com.sharmatechlabs.fruitblastquest.game.hint.HintController;
import com.sharmatechlabs.fruitblastquest.game.layer.grid.GridSystem;
import com.sharmatechlabs.fruitblastquest.game.layer.tile.TileSystem;
import com.sharmatechlabs.fruitblastquest.game.swap.SwapController;
import com.sharmatechlabs.fruitblastquest.ui.dialog.PauseDialog;
import com.nativegame.natyengine.Game;
import com.nativegame.natyengine.camera.FixedCamera;
import com.nativegame.natyengine.engine.Engine;
import com.nativegame.natyengine.input.touch.SingleTouchController;
import com.nativegame.natyengine.ui.GameActivity;
import com.nativegame.natyengine.ui.GameView;
import com.nativegame.natyengine.util.debug.EntityCounter;
import com.nativegame.natyengine.util.debug.FPSCounter;
import com.nativegame.natyengine.util.debug.UPSCounter;

/**
 * Created by Oscar Liang on 2022/02/23
 */


public class JuicyMatch extends Game {

    private static final boolean DEBUG_MODE = false;

    //--------------------------------------------------------
    // Constructors
    //--------------------------------------------------------
    public JuicyMatch(GameActivity activity, GameView gameView, Engine engine) {
        super(activity, gameView, engine);
        engine.setDebugMode(DEBUG_MODE);
        engine.setTouchController(new SingleTouchController(gameView));
        engine.setCamera(new FixedCamera(gameView.getWidth(), gameView.getHeight(), GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT));

        // Align the Camera to world center
        engine.getCamera().setCenterX(GameWorld.WORLD_WIDTH / 2f);
        engine.getCamera().setCenterY(GameWorld.WORLD_HEIGHT / 2f);

        // Init debug tools
        if (engine.isDebugMode()) {
            EntityCounter entityCounter = new EntityCounter(engine, 0, 200, 1000, 100);
            UPSCounter upsCounter = new UPSCounter(engine, 0, 0, 1000, 100);
            FPSCounter fpsCounter = new FPSCounter(engine, 0, 100, 1000, 100);
            int textSize = 100;
            int debugLayer = 12;
            entityCounter.setTextSize(textSize);
            upsCounter.setTextSize(textSize);
            fpsCounter.setTextSize(textSize);
            entityCounter.setLayer(debugLayer);
            upsCounter.setLayer(debugLayer);
            fpsCounter.setLayer(debugLayer);
            entityCounter.addToGame();
            upsCounter.addToGame();
            fpsCounter.addToGame();
        }

        // Init counter
        new ComboCounter(engine).addToGame();
        new ScoreCounter(activity, engine).addToGame();
        new StarCounter(activity, engine).addToGame();
        new MoveCounter(activity, engine).addToGame();
        new TargetCounter(activity, engine).addToGame();

        // Init layer
        new GridSystem(engine);
        TileSystem tileSystem = new TileSystem(engine);

        // Init Algorithm
        Algorithm regularTimeAlgorithm = new RegularTimeAlgorithm(engine, tileSystem,
                new LayerHandlerManager(engine), new TargetHandlerManager());
        Algorithm bonusTimeAlgorithm = new BonusTimeAlgorithm(engine, tileSystem);

        // Init controller
        new SwapController(engine, tileSystem).addToGame();
        new HintController(engine, tileSystem).addToGame();
        new BoosterCounter(activity, engine, tileSystem).addToGame();
        new GameController(activity, engine, regularTimeAlgorithm, bonusTimeAlgorithm).addToGame();
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onPause() {
        showPauseDialog();
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void showPauseDialog() {
        PauseDialog dialog = new PauseDialog(getActivity()) {
            @Override
            public void resumeGame() {
                resume();
            }

            @Override
            public void quitGame() {
                // Reduce one live
                ((MainActivity) getActivity()).getLivesTimer().reduceLive();
                getActivity().navigateBack();
            }
        };
        getActivity().showDialog(dialog);
    }
    //========================================================

}
