package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.utils.Mappers;

/** Movement System components is scaled by delta time
 *  Use MovementComponent velocity to deadend a body to sync the texture properly.
 * Created by Ron on 6/13/2016.
 */
public class MovementSystem extends IteratingSystem {

    private Vector2 temp = new Vector2();

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mappers.transform.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);

        Vector2 acceleration = temp.set(movement.acceleration).scl(deltaTime);
        movement.velocity.add(acceleration);

        Vector2 changeInVelocity = temp.set(movement.velocity).scl(deltaTime);
        transform.position.add(changeInVelocity);
    }

}
