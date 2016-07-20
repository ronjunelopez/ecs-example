package com.ronscript.overlap2dexample.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 7/2/2016.
 */
public abstract class Bullet extends Entity implements Poolable {

    protected World world;
    protected PooledEngine engine;
    public Entity entity;
    public BulletComponent bullet;
    public SizeComponent size;
    public TextureComponent texture;
    public PhysicsComponent physics;
    public TransformComponent transform;
    public MovementComponent movement;

    public Bullet() {
    }

    public void init(EntityBuilder factory){
        this.world = factory.getWorld();
        this.engine = factory.getEngine();
        entity = this.engine.createEntity();
        entity.flags = Constants.Flags.BULLET.ordinal();



        bullet = engine.createComponent(BulletComponent.class);
        size = engine.createComponent(SizeComponent.class);
        texture = engine.createComponent(TextureComponent.class);
        physics = engine.createComponent(PhysicsComponent.class);
        transform = engine.createComponent(TransformComponent.class);
        movement = engine.createComponent(MovementComponent.class);
    }

    public abstract void createBody();

    public void create() {
        createBody();
        entity.add(bullet);
        entity.add(size);
        entity.add(texture);
        entity.add(physics);
        entity.add(transform);
        entity.add(movement);
        if(Mappers.bulletFamily.matches(entity)) {
            engine.addEntity(entity);
        } else {
            Gdx.app.log("", "fail");
        }
    }

    @Override
    public void reset() {
//        transform.position.setZero();
        physics.body.setActive(false);
        movement.velocity.setZero();
        bullet.alive = true;
        bullet.fired = false;
    }
}
