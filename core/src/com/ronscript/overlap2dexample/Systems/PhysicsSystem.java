package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.Managers.BodyManager;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/17/2016.
 */
public class PhysicsSystem extends IntervalIteratingSystem implements Disposable{

    private World world;
    private BodyManager manager;

    public PhysicsSystem(World world, BodyManager manager) {
        super(Family.all(PhysicsComponent.class, TransformComponent.class).get(), Constants.TIME_STEP);
        this.world = world;
        this.manager = manager;
    }

    @Override
    protected void processEntity(Entity entity) {
        PhysicsComponent physics = Mappers.physics.get(entity);
        TransformComponent transform = Mappers.transform.get(entity);
        SizeComponent size = Mappers.size.get(entity);

        if(physics  != null) {
            Vector2 bodyPosition = physics.body.getPosition();
            transform.position.set(bodyPosition);
            transform.rotation = physics.body.getAngle() * MathUtils.radiansToDegrees;
        }

    }

    @Override
    protected void updateInterval() {
        world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
        super.updateInterval();
        manager.destroyBodies();
    }

    @Override
    public void dispose() {
        world.dispose();
    }
}
