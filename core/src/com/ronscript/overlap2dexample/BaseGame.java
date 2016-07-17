package com.ronscript.overlap2dexample;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.ronscript.overlap2dexample.Managers.ScreenManager;

/**
 * Created by Ron on 6/4/2016.
 */
public abstract class BaseGame extends Game {

    public AssetManager manager;
    public ScreenManager screenManager;

    public BaseGame(){

    }

    @Override
    public void create() {
        manager = new AssetManager();
        screenManager = new ScreenManager(this);
        screenManager.setScreen(ScreenManager.EnumScreen.SPLASH);
    }

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        screenManager.dispose();
    }

    @Override
    public void render() {
        super.render();
    }
}
