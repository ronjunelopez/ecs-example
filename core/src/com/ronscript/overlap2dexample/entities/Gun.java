package com.ronscript.overlap2dexample.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.states.GunState;

/**
 * @author Ron
 * @since 7/8/2016
 */
public class Gun{

    public Entity entity;
    /**
     *  Components
     */
    public GunComponent gun;
    public TextureComponent graphic;
    public SizeComponent size;
    public PhysicsComponent physics;
    public TransformComponent transform;
    public MovementComponent movement;
    public StateComponent state;
    public AIComponent ai;

    public Gun() {

    }
    public void sights(Vector2 target) {
        gun.sights.set(target);
    }
    public void trigger() {
        ai.stateMachine.changeState(GunState.FIRE);
    }
    public void reload() {
        float spatialX = transform.position.x;
        float spatialY = transform.position.y;


        switch(movement.state) {
            case NORTH:
                spatialX += size.width * 0.25f;
                spatialY += size.height;
                break;
            case SOUTH:
                spatialX += size.width * 0.25f;
                spatialY += 0;
                break;
            case WEST:
                spatialX += size.width * 0.85f;
                spatialY += size.height * 0.25f;
                break;
            case EAST:
                spatialX += -size.width * 0.20f;
                spatialY += size.height * 0.20f;
                break;
        }

        if(gun.cannonball == null) {
            gun.time += Gdx.graphics.getDeltaTime();
            if(gun.time <1) {
            } else {
                Cannonball cannonball = gun.cannonballPool.obtain();
                cannonball.init(gun.factory);
                cannonball.transform.position.set(new Vector2(spatialX, spatialY));
                cannonball.create();
                gun.cannonball = cannonball;
                gun.activeCannonball.add(cannonball);
                gun.time = 0;
                ai.stateMachine.changeState(GunState.IDLE);
            }
        }
    }
    public void fire() {
        GunComponent gun = this.gun;
        if(gun.cannonball == null) {
            ai.stateMachine.changeState(GunState.RELOAD);
        } else {
            gun.cannonball.bullet.fired = true;
            gun.cannonball.movement.velocity.set(getVelocity(gun.TRIGGER_SPEED, gun.cannonball.physics.body.getPosition(), gun.sights));
            gun.cannonball = null;
        }
    }

    public Vector2 getVelocity(float speed, Vector2 currentPosition, Vector2 targetPosition) {
        // Copied sights position and subtracted by bullet position
        Vector2 targetDirection = targetPosition.cpy().sub(currentPosition);
        return targetDirection
                .nor() // normalize to avoid getting the direction as speed
                .scl(speed);  // scaled by speed
    }

    public void updateChildTransform(float x, float y, int z) {
        if(gun.cannonball != null) {
            Cannonball cannonball = gun.cannonball;
            cannonball.physics.body.setTransform(new Vector2(transform.position.x  + x, transform.position.y  + y), 0);
            cannonball.texture.z = z;
        }
    }
    public void updateChildLinearVelocity() {
        if(gun.cannonball != null) {
            Cannonball cannonball = gun.cannonball;
            cannonball.movement.velocity.set(movement.velocity);
            cannonball.physics.body.setLinearVelocity(cannonball.movement.velocity);
        }
    }
}
