package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.CharacterComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.PlayerControlledComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.Gun;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/17/2016.
 */
public class CharacterSystem extends IteratingSystem {

    public CharacterSystem() {
        super(Family.all(
                CharacterComponent.class,
                PlayerControlledComponent.class,
                MovementComponent.class,
                StateComponent.class,
                AnimationComponent.class,
                PhysicsComponent.class,
                TransformComponent.class
        ).get());


    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        updatePlayer(entity);
    }

    private void updatePlayer(Entity e){
        CharacterComponent player = Mappers.character.get(e);
        MovementComponent movement = Mappers.movement.get(e);
        StateComponent state = Mappers.state.get(e);
        AnimationComponent animation = Mappers.animation.get(e);
        PhysicsComponent physics = Mappers.physics.get(e);
        TransformComponent transform = Mappers.transform.get(e);
        SizeComponent size = Mappers.size.get(e);
        TextureComponent renderable = Mappers.texture.get(e);

        updateMovement(e);
        if(player.gun != null)
        updateWeapon(player,movement, transform, renderable , size);
        // @TODO change to Weapon
        if(player.attack) { //@TODO change to ATTACK STATE
//            Gdx.app.log("", "daw");
            Gun gun = player.gun;
            gun.sights(player.target);
            gun.trigger();
            player.attack = false;
        }
    }

    private void updateWeapon(CharacterComponent player, MovementComponent movement, TransformComponent transform, TextureComponent renderable, SizeComponent size){
        Gun gun = player.gun;
        Vector2 position = transform.position.cpy();
        switch (movement.state) {
            case NORTH:
//                gun.updateChildTransform(
//                        gun.size.width  / 2,
//                        gun.size.height,
//                        -1
//                        );
//                position.add(10 * , 24);gun.transform.position.set(position);
                gun.graphic.z = renderable.z  - 1;
                gun.movement.state = MovementComponent.State.NORTH;
                break;
            case SOUTH:
//                gun.updateChildTransform(gun.size.width * WorldUtils.pixelsToMetres / 2,0f, 1) ;
//                position.add(10, -7);gun.transform.position.set(position);
                gun.movement.state = MovementComponent.State.SOUTH;
                break;
            case WEST:
//                gun.updateChildTransform(gun.size.width * WorldUtils.pixelsToMetres, gun.size.height * WorldUtils.pixelsToMetres / 2, 1) ;
//                position.add(23, 5);gun.transform.position.set(position);
                gun.movement.state = MovementComponent.State.WEST;
                break;
            case EAST:
//                gun.updateChildTransform(0, gun.size.height * WorldUtils.pixelsToMetres / 2, 1) ;
//                position.add(-9, 5);gun.transform.position.set(position);
                gun.movement.state = MovementComponent.State.EAST;
                break;
            case NORTH_WEST:
//                position.add(10, -2);gun.transform.position.set(position);
                gun.movement.state = MovementComponent.State.NORTH_WEST;
                break;
            case NORTH_EAST:
//                position.add(10, -2);gun.transform.position.set(position);
                gun.movement.state = MovementComponent.State.NORTH_EAST;
                break;
            case SOUTH_WEST:
//                position.add(10, -2);gun.transform.position.set(position);
                gun.movement.state = MovementComponent.State.SOUTH_WEST;
                break;
            case SOUTH_EAST:
//                position.add(10, -2);gun.transform.position.set(position);
                gun.movement.state = MovementComponent.State.SOUTH_EAST;
                break;
            case IDLE:
                break;


        }

        gun.transform.position.set(transform.position);
        gun.movement.velocity.set(movement.velocity);

        gun.updateChildLinearVelocity();
    }

    private void updateMovement(Entity entity){
        getEngine().getSystem(MovementSystem.class).setProcessing(false); // Disable first the MovementSystem to not confuse the positioning, because we are using MovementComponent to move a body
        CharacterComponent player = Mappers.character.get(entity);
        PhysicsComponent physics = Mappers.physics.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);

        AnimationComponent animation = Mappers.animation.get(entity);
        StateComponent state = Mappers.state.get(entity);

        final float VELOCITY = player.MOVE_VELOCITY;
        switch (movement.state){
            case NORTH:
                // @TODO also update child entities here (Ex. weapon, armor, pet)
                animation.isLooping = true;
                state.setState(MovementComponent.State.NORTH.ordinal());
                movement.velocity.y = VELOCITY;
                movement.velocity.x = 0;
                break;
            case SOUTH:
                animation.isLooping = true;
                state.setState(MovementComponent.State.SOUTH.ordinal());
                movement.velocity.y = -VELOCITY;
                movement.velocity.x = 0;
                break;
            case EAST:
                animation.isLooping = true;
                state.setState(MovementComponent.State.EAST.ordinal());
                movement.velocity.x = -VELOCITY;
                movement.velocity.y = 0;
                break;
            case WEST:
                animation.isLooping = true;
                state.setState(MovementComponent.State.WEST.ordinal());
                movement.velocity.y = 0;
                movement.velocity.x = VELOCITY;
                break;
            case NORTH_EAST:
                animation.isLooping = true;
                state.setState(MovementComponent.State.NORTH_EAST.ordinal());
                movement.velocity.y = VELOCITY;
                movement.velocity.x = -VELOCITY;
                break;
            case NORTH_WEST:
                animation.isLooping = true;
                state.setState(MovementComponent.State.NORTH_WEST.ordinal());
                movement.velocity.y = VELOCITY;
                movement.velocity.x = VELOCITY;
                break;
            case SOUTH_EAST:
                animation.isLooping = true;
                state.setState(MovementComponent.State.SOUTH_EAST.ordinal());
                movement.velocity.y = -VELOCITY;
                movement.velocity.x = -VELOCITY;
                break;
            case SOUTH_WEST:
                animation.isLooping = true;
                state.setState(MovementComponent.State.SOUTH_WEST.ordinal());
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
}
