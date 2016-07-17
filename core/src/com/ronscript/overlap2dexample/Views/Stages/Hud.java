package com.ronscript.overlap2dexample.Views.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.ronscript.overlap2dexample.utils.Constants;

/**
 * Created by Ron on 6/27/2016.
 */
public class Hud implements Disposable{

    private Stage stage;
    private SpriteBatch batch;

    public Hud(SpriteBatch batch) {
        this.batch = batch;
//        stage = new Stage(new FillViewport(Constants.WIDTH,Constants.HEIGHT, new OrthographicCamera()),batch);
        stage = new Stage(new FitViewport(Constants.WIDTH,Constants.HEIGHT, new OrthographicCamera()),batch);
        Skin skin = new Skin();

//        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
//        pixmap.setColor(Color.WHITE);
//        pixmap.fill();
//
//        skin.add("bg", new Texture(pixmap));

        Table table = new Table(skin);
//        table.setBackground(skin.getDrawable("bg"));
        table.top();
        table.setFillParent(true);

        Label playerName = new Label("PLAYER NAME:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label playerLife = new Label("PLAYER LIFE:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label playerScore = new Label("PLAYER SCORE:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(playerName).expandX();
        table.add(playerLife).expandX();
        table.add(playerScore).expandX();

        stage.addActor(table);
    }


    public Stage getStage(){
        return stage;
    }

    public void update(float deltaTime){
        batch.setProjectionMatrix(stage.getCamera().combined);
        stage.act(deltaTime);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
