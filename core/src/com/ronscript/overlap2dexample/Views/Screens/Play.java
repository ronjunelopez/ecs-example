package com.ronscript.overlap2dexample.Views.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ronscript.overlap2dexample.BaseGame;
import com.ronscript.overlap2dexample.BaseScreen;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.Managers.GameWorld;
import com.ronscript.overlap2dexample.Managers.ScreenManager;
import com.ronscript.overlap2dexample.utils.Constants;

/**
 * Created by Ron on 6/5/2016.
 */
public class Play extends BaseScreen {


    SpriteBatch batch;
    GameWorld gameWorld;

    Viewport gameViewport;

//    Hud hud;

    public Play(BaseGame game) {
        super(game);
        hideLoading = false;
        batch = new SpriteBatch();
        gameWorld = new GameWorld(batch);
    }

    @Override
    public boolean onLoadAsset(AssetManager manager) {
        GameAssets.load(manager);
        return true;
    }

    @Override
    public void onCreateAsset(AssetManager manager) {
        GameAssets.create(this,manager);
    }

    @Override
    public void onShow() {
        gameWorld.setProcessing(true);
        gameWorld.buildWorld();
        gameViewport = new FitViewport(Constants.FRUSTUM_WIDTH, Constants.FRUSTUM_HEIGHT, gameWorld.getCamera());
        gameWorld.getCamera().position.set(gameViewport.getWorldWidth()/2, gameViewport.getWorldHeight()/2, 0);
    }

    @Override
    public void onResize(int width, int height) {
//        hud.getViewport().update(width, height, true);
        gameViewport.update(width, height);
    }

    @Override
    public void pause() {
        Gdx.app.log("Play Screen", "pause");
        gameWorld.setProcessing(false);
    }

    @Override
    public void resume() {
        Gdx.app.log("Play Screen", "resume");
        gameWorld.setProcessing(true);
    }

    @Override
    public void hide() {
        Gdx.app.log("Play Screen", "hidden");
        gameWorld.setProcessing(false);
    }

    @Override
    public void onRender(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameViewport.apply();
        gameWorld.getEngine().update(delta);
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.screenManager.setScreen(ScreenManager.EnumScreen.SPLASH);
        }
    }

    @Override
    public void onDispose() {
        gameWorld.dispose();
        Gdx.app.log("Play Screen", "disposed");
    }

}
