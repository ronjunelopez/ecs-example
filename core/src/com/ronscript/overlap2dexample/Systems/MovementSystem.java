package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/13/2016.
 */
public class MovementSystem extends IntervalIteratingSystem {

    private Vector2 temp = new Vector2();

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get(), Constants.TIME_STEP);
    }

    @Override
    protected void processEntity(Entity entity) {
        TransformComponent transform = Mappers.transform.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);

        Vector2 acceleration = temp.set(movement.accel).scl(0.5f);
        movement.velocity.add(acceleration);

        Vector2 acceleratedVelocity = temp.set(movement.velocity).scl(0.5f);
        transform.position.add(acceleratedVelocity);
    }

}
