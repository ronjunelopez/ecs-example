package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ronscript.overlap2dexample.Components.CharacterComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PlayerControlledComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.Listeners.PlayerInputAdapter;
import com.ronscript.overlap2dexample.Views.Stages.Dashboard;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/28/2016.
 */
public class InputSystem extends IteratingSystem {

    private boolean debug = false;
    Dashboard dashboard;
    PlayerInputAdapter input;

    public InputSystem(SpriteBatch batch, Camera camera) {
        super(Family.all(CharacterComponent.class, TransformComponent.class, MovementComponent.class, PlayerControlledComponent.class).get());
        dashboard = new Dashboard(batch);
        dashboard.getStage().setDebugAll(debug);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(dashboard.getStage());
        input = new PlayerInputAdapter(camera);
        multiplexer.addProcessor(input);
        Gdx.input.setInputProcessor(multiplexer);
    }

    private float interval = 0.1f;
    private float accumulator = 0;

    private Array<Entity> entityQueue = new Array<Entity>();

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        entityQueue.add(entity);
        MovementComponent movement = Mappers.movement.get(entity);
        processPlayerInput(movement);

    }

    private void updateInterval(float deltaTime) {
        accumulator += deltaTime;
        if(accumulator >= interval){
            accumulator -= interval;
            for (Entity entity: entityQueue) {
                CharacterComponent character = Mappers.character.get(entity);
                if (input.isTouched()) {
                    Vector2 touchpoint = input.getGameTouchpointInMeters();
                    character.target.set(touchpoint);
                    character.attack = true;
                } else {
                    character.attack = false;
                }
            }
            entityQueue.clear();
        }
    }

    public void processPlayerInput(MovementComponent movement){

        switch (dashboard.getKnobDirection()){
            case NORTH: break;
            case SOUTH: break;
            case EAST: break;
            case WEST: break;
            case NORTH_EAST: break;
            case NORTH_WEST: break;
            case SOUTH_EAST: break;
            case SOUTH_WEST: break;
            case NONE:
//                character.state = CharacterComponent.MoveState.NONE;
                break;
        }

        if (input.isKeyUp()) {
            movement.state = MovementComponent.State.NORTH;
        }
        if (input.isKeyDown()) {
            movement.state = MovementComponent.State.SOUTH;
        }

        if (input.isKeyRight()) {
            movement.state = MovementComponent.State.WEST;
        }
        if (input.isKeyLeft()) {
            movement.state = MovementComponent.State.EAST;
        }
        if (input.isKeyUp() && input.isKeyLeft()) {
            movement.state = MovementComponent.State.NORTH_EAST;
        }
        if (input.isKeyUp() && input.isKeyRight()) {
            movement.state = MovementComponent.State.NORTH_WEST;
        }
        if (input.isKeyDown() && input.isKeyLeft()) {
            movement.state = MovementComponent.State.SOUTH_EAST;
        }
        if (input.isKeyDown() && input.isKeyRight()) {
            movement.state = MovementComponent.State.SOUTH_WEST;
        }


        if(!input.isKeyDown() && !input.isKeyLeft() && !input.isKeyRight() && !input.isKeyUp()){
            movement.state = MovementComponent.State.IDLE;
        }

        if(input.isPressed()) {
//            animation.isLooping = true;
        } else {
//            animation.isLooping = false;
        }
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateInterval(deltaTime);
        dashboard.update(deltaTime);
    }
}
