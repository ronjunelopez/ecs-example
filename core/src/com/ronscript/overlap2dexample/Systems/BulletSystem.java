package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Managers.CameraManager;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 7/3/2016.
 */
public class BulletSystem extends IteratingSystem {

    EntityBuilder factory;

    public BulletSystem(EntityBuilder factory) {
        super(Mappers.bulletFamily);
        this.factory = factory;
    }

    private boolean isOutOfScreen(Vector2 position, float offset){
        return CameraManager.getInstance().isOutsideCamera(position, offset);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BulletComponent bullet = Mappers.bullet.get(entity);
        PhysicsComponent physics = Mappers.physics.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);

        Gdx.app.log("", "" + factory.getEngine().getEntitiesFor(Mappers.bulletFamily).size());
//        deadend(entity, deltaTime);

        Body body = physics.body;

        Vector2 bulletPosition = body.getPosition();

//        float distance = bulletPosition.dst(bullet.destination);  // scaled by speed);
        ;
        /*
            if the speed is 1 meter the distance moved is the value of TIME_STEP
            defined precision is speed multiply by TIME_STEP
        */

//        Gdx.app.log("", "" + distance);
//        float DEFINED_PRECISION = Constants.TIME_STEP / 2;

        if(bullet.alive) {
            if (bullet.fired) {
                body.setLinearVelocity(movement.velocity.set(getVelocity(6, bulletPosition, bullet.destination)));
                bullet.fired = false;
            }
        } else {
            /*
                TODO when the body stop the position is not accurate.
             */
            body.setLinearVelocity(0,0);
            bullet.alive = false;
        }
        float bulletOffset = 6; // 6 meters
        if(isOutOfScreen(bulletPosition, bulletOffset)){
            bullet.alive = false;
        }
    }

    public Vector2 getVelocity(float speed, Vector2 currentPosition, Vector2 targetPosition) {
        // Copied sights position and subtracted by bullet position
        Vector2 targetDirection = targetPosition.cpy().sub(currentPosition);
        return targetDirection
                .nor() // normalize to avoid getting the direction as speed
                .scl(speed);  // scaled by speed
    }

//    private void deadend(Entity entity, float deltaTime) {
//        BulletComponent bullet = Mappers.bullet.get(entity);
//        PhysicsComponent physics = Mappers.physics.get(entity);
//
//        Body body = physics.body;
//        Vector2 bulletPosition = body.getPosition();
//
//        // sights is in PIXEL value so it needs to be convert to METER
//        Vector2 targetPosition = new Vector2(bullet.targetX * WorldUtils.pixelsToMetres, bullet.targetY * WorldUtils.pixelsToMetres);
//        // Copied sights position and subtracted by bullet position
//        Vector2 targetDirection = targetPosition.cpy().sub(bulletPosition);
//
//        float distance = bulletPosition.cpy().dst(targetPosition);
//
//        /*
//            Move body by Linear Velocity (@TODO use in rocket ammo)
//         */
//        float speed = 16;
//        Vector2 velocity = targetDirection
//                .cpy() // Copied sights direction
//                .nor() // normalize to avoid getting the direction as speed
//                .scl(speed);  // scaled by speed
//
//        // the distance is not accurate, so we get the time step as defined precision
//        float DEFINED_PRECISION = Constants.TIME_STEP;
//        // check if the bullet is near or maybe match the touch point
//        if(distance >= DEFINED_PRECISION) {
//            // deadend the bullet
//            body.setLinearVelocity(velocity);
//        } else {
//            // stop the bullet
//            body.setLinearVelocity(0,0);
//        }
//       /*
//            Move body by Linear Impulse (@TODO use in rocket ammo)
//        */
////        float mass = body.getMass();
////        float targetVelocity = 16.6667f; // 16.6667 metres per second
////
////        float impulseMag = mass * targetVelocity;
////
////        Vector2 impulse = targetDirection.cpy().nor().scl(impulseMag);
////
////        body.applyLinearImpulse(impulse, body.getWorldCenter(), true);
//        /*
//            Move body by Force (@TODO use in rocket ammo)
//         */
////        Vector2 bulletVelocity = body.getLinearVelocity();
////        float speed = 16;
////        Vector2 desiredVelocity = targetDirection.cpy().nor().scl(speed); // normalize means actual speed then scale to desired speed
////        Vector2 changeInVelocity = desiredVelocity.sub(bulletVelocity);
////        float forceX  = mass * changeInVelocity.x / Constants.TIME_STEP; // f = mv/t
////        float forceY  = mass * changeInVelocity.y / Constants.TIME_STEP; // f = mv/t
////        Vector2 force = new Vector2(forceX, forceY);
////        body.applyForce(force.scl(deltaTime), body.getWorldCenter(), true);
//
//    }



}
