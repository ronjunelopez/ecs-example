package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * @author Ron
 * @since 7/20/2016
 */
public class PooledBullet implements Poolable {


    public Entity entity;
    EntityBuilder factory;
    final PooledEngine engine;

    public PooledBullet() {
        factory = EntityBuilder.getInstance();
        engine = factory.getEngine();
//        engine.getSystem(MovementSystem.class).setProcessing(false);
    }

    public void init(float x, float y) {
        entity = factory.createEntity(Constants.Flags.BULLET);
        BulletComponent bullet = engine.createComponent(BulletComponent.class);
        SizeComponent size = engine.createComponent(SizeComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);

        texture.region = GameAssets.cannonball;
        transform.position.set(x, y);
        size.width = 0.25f;
        size.height = 0.25f;
        transform.origin.set(size.width / 2, size.height / 2);
        physics.body = factory.createBulletBody(entity, x, y);

        entity.add(bullet);
        entity.add(size);
        entity.add(texture);
        entity.add(physics);
        entity.add(transform);
        entity.add(movement);

        try {
            engine.addEntity(entity);
        } catch (Exception e) {

        }
    }

    @Override
    public void reset() {
        factory.removeEntity(entity);
        MovementComponent movement = Mappers.movement.get(entity);
        PhysicsComponent physics = Mappers.physics.get(entity);
        BulletComponent bullet = Mappers.bullet.get(entity);

//        physics.body.setActive(false);
        movement.velocity.setZero();
        bullet.alive = true;
        bullet.fired = false;
    }

}
