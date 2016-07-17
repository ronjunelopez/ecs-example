package com.ronscript.overlap2dexample.Views.Stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * Created by Ron on 7/4/2016.
 */
public class GameStage extends Stage {

    private final float VIEWPORT_WIDTH = Constants.WIDTH * WorldUtils.pixelsToMetres;
    private final float VIEWPORT_HEIGHT = Constants.HEIGHT * WorldUtils.pixelsToMetres;

    private OrthographicCamera camera;
    private Viewport viewport;

    private HudWidget hud;
    private GamePad gamepad;
    public GameStage() {
        setupCamera();
        setupHud();
        setupGamepad();
    }

    private void setupCamera(){
        camera = new OrthographicCamera();
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
        camera.position.set(viewport.getWorldWidth() * 0.5f , viewport.getWorldHeight() * 0.5f , 0);
        camera.update();
    }
    private void setupHud(){
        hud = new HudWidget();
        addActor(hud);
    }
    private void setupGamepad(){
        gamepad = new GamePad();
        addActor(gamepad);
    }
}
