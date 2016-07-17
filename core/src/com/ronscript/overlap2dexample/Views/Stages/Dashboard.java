package com.ronscript.overlap2dexample.Views.Stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ronscript.overlap2dexample.utils.Constants;

/**
 * Created by Ron on 6/5/2016.
 */
public class Dashboard implements Disposable {

    private Stage stage;

    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Drawable touchBackground;
    private Drawable touchKnob;

    private SpriteBatch batch;
    private Viewport viewport;

    public enum KnobDirection {
        WEST, NORTH_WEST, NORTH,NORTH_EAST,EAST,SOUTH_EAST, SOUTH, SOUTH_WEST, NONE
    }

    public Dashboard(SpriteBatch batch) {
        this.batch = batch;

        viewport = new FitViewport(Constants.WIDTH, Constants.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, batch);

        Skin skin = new Skin();
        skin.add("touchBackground", new Texture("data/touchpad_background.png"));
        skin.add("touchKnob", new Texture("data/touchpad_knob.png"));

        touchpadStyle = new Touchpad.TouchpadStyle();

        touchBackground = skin.getDrawable("touchBackground");
        touchKnob = skin.getDrawable("touchKnob");


        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        touchpad = new Touchpad(10 , touchpadStyle);
        touchpad.setBounds(0,0,96,96);


//        MoveToAction action = Actions.action(MoveToAction.class);
//        action.setPosition(10f, 0);
//        action.setDuration(5f);
//        action.setInterpolation(Interpolation.bounceOut);
//        touchpad.addAction(action);

        stage.addActor(touchpad);

    }

    public Touchpad getTouchpad(){
        return touchpad;
    }

    public float getKnobAngle(){
        Vector2 knob = new Vector2(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        return knob.angle();
    }



    public KnobDirection getKnobDirection(){
        KnobDirection direction = KnobDirection.NONE;
        float knobAngle = getKnobAngle();

         if(knobAngle > 0 && knobAngle < 22.5 || knobAngle > 337.5 && knobAngle < 360){
             direction = KnobDirection.WEST;
         }

        if(knobAngle > 22.5 && knobAngle <67.5) {
            direction = KnobDirection.NORTH_WEST;
        }

        if(knobAngle > 67.5 && knobAngle < 112.5){
            direction = KnobDirection.NORTH;
        }

        if(knobAngle > 112.5 && knobAngle < 157.5){
            direction = KnobDirection.NORTH_EAST;
        }

        if(knobAngle > 157.5 && knobAngle < 202.5){
            direction = KnobDirection.EAST;
        }

        if(knobAngle > 202.5 && knobAngle < 247.5){
            direction = KnobDirection.SOUTH_EAST;
        }

        if(knobAngle > 247.5 && knobAngle < 292.5) {
            direction = KnobDirection.SOUTH;
        }

        if(knobAngle > 292.5 && knobAngle < 336.5) {
            direction = KnobDirection.SOUTH_WEST;
        }
        return direction;
    }

    public Viewport getViewport(){
        return  viewport;
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
