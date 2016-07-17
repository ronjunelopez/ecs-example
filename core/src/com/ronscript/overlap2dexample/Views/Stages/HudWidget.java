package com.ronscript.overlap2dexample.Views.Stages;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by Ron on 7/4/2016.
 */
public class HudWidget extends Table {

    public HudWidget() {
        top();
        setFillParent(true);

        Label playerName = new Label("PLAYER NAME:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label playerLife = new Label("PLAYER LIFE:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label playerScore = new Label("PLAYER SCORE:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        add(playerName).expandX();
        add(playerLife).expandX();
        add(playerScore).expandX();
    }
}
