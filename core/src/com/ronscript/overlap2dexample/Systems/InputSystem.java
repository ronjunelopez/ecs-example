package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ronscript.overlap2dexample.Components.InputComponent;
import com.ronscript.overlap2dexample.Listeners.PlayerInputAdapter;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/28/2016.
 */
public class InputSystem extends IteratingSystem {

    private boolean debug = false;
    //    Dashboard dashboard;
    PlayerInputAdapter inputA;

    public InputSystem(SpriteBatch batch, Camera camera) {
        super(Family.all(InputComponent.class).get());
//        dashboard = new Dashboard(batch);
//        dashboard.getStage().setDebugAll(debug);
        InputMultiplexer multiplexer = new InputMultiplexer();
//        multiplexer.addProcessor(dashboard.getStage());
        inputA = new PlayerInputAdapter(camera);
        multiplexer.addProcessor(inputA);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        InputComponent input = Mappers.input.get(entity);

        input.keyUp = inputA.isKeyUp();
        input.keyDown = inputA.isKeyDown();
        input.keyLeft = inputA.isKeyLeft();
        input.keyRight = inputA.isKeyRight();
        input.pressed = inputA.isPressed();
        input.touched = inputA.isTouched();
        input.touchpoint.set(inputA.getTouchpoint());
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
//        dashboard.update(deltaTime);
    }
}
