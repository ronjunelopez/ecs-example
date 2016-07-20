package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.CharacterComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.InputComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.states.GunState;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/17/2016.
 */
public class CharacterSystem extends IteratingSystem {

    public CharacterSystem() {
        super(Family.all(
                CharacterComponent.class,
                InputComponent.class,
                MovementComponent.class,
                StateComponent.class,
                AnimationComponent.class,
                PhysicsComponent.class,
                TransformComponent.class,
                SizeComponent.class
        ).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        updateMovement(entity);
        onPlayerAttack(entity);
        onPlayerInput(entity);
    }
    private void updateMovement(Entity entity){
//        getEngine().getSystem(MovementSystem.class).setProcessing(false); // Disable first the MovementSystem to not confuse the positioning, because we are using MovementComponent to deadend a body
        CharacterComponent character = Mappers.character.get(entity);
        PhysicsComponent physics = Mappers.physics.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);

        AnimationComponent animation = Mappers.animation.get(entity);
        StateComponent state = Mappers.state.get(entity);
        TransformComponent transform = Mappers.transform.get(entity);

        final float VELOCITY = character.MOVE_VELOCITY;
        switch (movement.state){
            case NORTH:
                // @TODO also update child entities here (Ex. weapon, armor, pet)
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.NORTH.ordinal());
                movement.velocity.y = VELOCITY;
                movement.velocity.x = 0;
                break;
            case SOUTH:
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.SOUTH.ordinal());
                movement.velocity.y = -VELOCITY;
                movement.velocity.x = 0;
                break;
            case EAST:
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.EAST.ordinal());
                movement.velocity.x = -VELOCITY;
                movement.velocity.y = 0;
                break;
            case WEST:
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.WEST.ordinal());
                movement.velocity.y = 0;
                movement.velocity.x = VELOCITY;
                break;
            case NORTH_EAST:
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.NORTH_EAST.ordinal());
                movement.velocity.y = VELOCITY;
                movement.velocity.x = -VELOCITY;
                break;
            case NORTH_WEST:
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.NORTH_WEST.ordinal());
                movement.velocity.y = VELOCITY;
                movement.velocity.x = VELOCITY;
                break;
            case SOUTH_EAST:
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.SOUTH_EAST.ordinal());
                movement.velocity.y = -VELOCITY;
                movement.velocity.x = -VELOCITY;
                break;
            case SOUTH_WEST:
                animation.isLooping = true;
                state.setState(MovementComponent.Direction.SOUTH_WEST.ordinal());
                movement.velocity.y = -VELOCITY;
                movement.velocity.x = VELOCITY;
                break;
            default:
                animation.isLooping = false;
                movement.velocity.y = 0;
                movement.velocity.x = 0;
                break;
        }

        physics.body.setLinearVelocity(movement.velocity);
    }

    private void onPlayerAttack(Entity entity) {
        CharacterComponent character = Mappers.character.get(entity);
        NodeComponent node = Mappers.node.get(entity);
        if (character.attack) { // @TODO change to CharacterState.ATTACK
            Entity weapon = node.getChild("weapon");
            if (weapon == null) {
                return;
            }
            AIComponent ai = Mappers.ai.get(weapon);
            GunComponent gun = Mappers.gun.get(weapon);
            gun.sights.set(character.target);
            ai.fsm.changeState(GunState.FIRE);
            character.attack = false;
        }
    }

    private void onPlayerInput(Entity entity) {

        InputComponent input = Mappers.input.get(entity);

        MovementComponent movement = Mappers.movement.get(entity);

        if (input.keyUp) {
            movement.state = MovementComponent.Direction.NORTH;
        }
        if (input.keyDown) {
            movement.state = MovementComponent.Direction.SOUTH;
        }
        if (input.keyRight) {
            movement.state = MovementComponent.Direction.WEST;
        }
        if (input.keyLeft) {
            movement.state = MovementComponent.Direction.EAST;
        }
        if (input.keyUp && input.keyLeft) {
            movement.state = MovementComponent.Direction.NORTH_EAST;
        }
        if (input.keyUp && input.keyRight) {
            movement.state = MovementComponent.Direction.NORTH_WEST;
        }
        if (input.keyDown && input.keyLeft) {
            movement.state = MovementComponent.Direction.SOUTH_EAST;
        }
        if (input.keyDown && input.keyRight) {
            movement.state = MovementComponent.Direction.SOUTH_WEST;
        }
        if (!input.keyDown && !input.keyLeft && !input.keyRight && !input.keyUp) {
            movement.state = MovementComponent.Direction.IDLE;
        }

        CharacterComponent character = Mappers.character.get(entity);
        if (input.touched) {

            Vector2 touchPoint = new Vector2(input.touchpoint.x, input.touchpoint.y);
            character.target.set(touchPoint);
            character.attack = true;
        } else {
            character.attack = false;
        }
    }
}
