package com.ronscript.overlap2dexample.Views.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.ronscript.overlap2dexample.BaseGame;
import com.ronscript.overlap2dexample.BaseScreen;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.Managers.ScreenManager;

/**
 * Created by Ron on 6/4/2016.
 */
public class Splash extends BaseScreen{
    Stage stage;

    public Splash(BaseGame game) {
        super(game);
    }

    @Override
    public boolean onLoadAsset(AssetManager manager) {
        return false;
    }

    @Override
    public void onCreateAsset(AssetManager manager) {

    }

    @Override
    public void onShow() {
        stage = new Stage();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        Label message = new Label("Splash",style);


        GameAssets.loadLoadingSkin();
        Skin skin = GameAssets.loadingSkin;
        Table table = new Table();
        table.setBackground(skin.getDrawable("bg"));
        table.center();
        table.setFillParent(true);
        table.add(message).expandX();
        table.row();

        stage.addActor(table);
    }

    @Override
    public void onResize(int width, int height) {

    }

    @Override
    public void onRender(float delta) {
        stage.act(delta);
        stage.draw();

        if(Gdx.input.isTouched()){
            game.screenManager.setScreen(ScreenManager.EnumScreen.PLAY);
        }
    }

    @Override
    public void onDispose() {
        stage.dispose();
    }
}
