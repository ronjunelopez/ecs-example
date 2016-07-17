package com.ronscript.overlap2dexample.Views.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ronscript.overlap2dexample.BaseGame;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.utils.Constants;

/**
 * Created by Ron on 6/5/2016.
 */
public class Loading implements Disposable{

    private Viewport viewport;
    final BaseGame game;
    private Stage stage;
    private ProgressBar progressBar;
    private float progress;
    private boolean isFinished;

    public Loading(final BaseGame game) {
        isFinished = false;
        this.game = game;
        viewport = new FillViewport(Constants.WIDTH, Constants.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
    }

    public void create(){
        GameAssets.loadLoadingSkin();
        Skin skin = GameAssets.loadingSkin;
        Drawable background = skin.getDrawable("barBg");
        Drawable knob = skin.getDrawable("knob");
        Drawable knobBefore = skin.getDrawable("bar");
        knob.setMinHeight(32);
        knobBefore.setMinHeight(32);

        ProgressBar.ProgressBarStyle progressStyle =  new ProgressBar.ProgressBarStyle();
        progressStyle.background = background;
        progressStyle.knob = knob;
        progressStyle.knobBefore = knobBefore;

        progressBar = new ProgressBar(0,1,0.1f,false,progressStyle);

        BitmapFont font = new BitmapFont();

        Label assetNamesLabel = new Label("Loading...", new Label.LabelStyle(font, Color.WHITE));
        Table table = new Table();
        table.setBackground(skin.getDrawable("bg"));
        table.setFillParent(true);
        table.add(progressBar).size(256,32);
        table.row();
        table.add(assetNamesLabel).expandX();

        stage.addActor(table);
    }


    public void render(float delta){
        if(progress >0.99 && game.manager.update()){
            isFinished = true;
        }
        if(!isFinished) {
            progress = Interpolation.linear.apply(progress, game.manager.getProgress(), 0.1f);
            progressBar.setValue(progress);
            stage.act(delta);
            stage.draw();
        }
    }

    public void resize(int width, int height){
        stage.getViewport().update(width,height,true); //center
    }

    public boolean isLoaded(){
        return isFinished;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
