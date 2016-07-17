package com.ronscript.overlap2dexample;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.ronscript.overlap2dexample.Views.Stages.Loading;

/**
 * Created by Ron on 6/5/2016.
 */
public abstract class BaseScreen implements Screen {

    protected final BaseGame game;

    protected Loading loading;
    protected boolean hideLoading = true;

    public BaseScreen(final BaseGame game) {
        this.game = game;
        loading = new Loading(this.game);
    }

    public abstract boolean onLoadAsset(AssetManager manager);
    public abstract void onCreateAsset(AssetManager manager);
    public abstract void onShow();
    public abstract void onResize(int width, int height);
    public abstract void onRender(float delta);
    public abstract void onDispose();


    @Override
    public void show() {
            loading.create();
        if(onLoadAsset(game.manager)) {
            game.manager.finishLoading();
        }
        onCreateAsset(game.manager);
        onShow();
    }
    @Override
    public void render(float delta) {
        if(!hideLoading){
            if (loading.isLoaded()) {
                onRender(delta);
            } else {
                loading.render(delta);
            }
        } else {
            onRender(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        loading.resize(width,height);
        onResize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.manager.dispose();
        loading.dispose();
        onDispose();
    }
}
