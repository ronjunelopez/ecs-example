package com.ronscript.overlap2dexample.Views.Stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

/**
 * Created by Ron on 7/4/2016.
 */
public class GamePad extends Table {

    private Touchpad touchpad;

    public GamePad() {
        bottom();
        setFillParent(true);
        setupTouchpad();
    }

    private void setupTouchpad(){
        Skin skin = new Skin();
        skin.add("touchBackground", new Texture("data/touchpad_background.png"));
        skin.add("touchKnob", new Texture("data/touchpad_knob.png"));

        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();

        touchpadStyle.background = skin.getDrawable("touchBackground");
        touchpadStyle.knob =  skin.getDrawable("touchKnob");

        touchpad = new Touchpad(10 , touchpadStyle);
        touchpad.setBounds(0,0,96,96);
        add(touchpad);
    }
}
