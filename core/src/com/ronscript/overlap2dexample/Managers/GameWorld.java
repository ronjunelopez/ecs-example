package com.ronscript.overlap2dexample.Managers;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.ronscript.overlap2dexample.Listeners.AnimationSystemEntityListener;
import com.ronscript.overlap2dexample.Listeners.BodyRemovalListener;
import com.ronscript.overlap2dexample.Listeners.WorldContactListener;
import com.ronscript.overlap2dexample.Systems.AnimationSystem;
import com.ronscript.overlap2dexample.Systems.BulletSystem;
import com.ronscript.overlap2dexample.Systems.CameraSystem;
import com.ronscript.overlap2dexample.Systems.GunSystem;
import com.ronscript.overlap2dexample.Systems.MovementSystem;
import com.ronscript.overlap2dexample.Systems.NodeSystem;
import com.ronscript.overlap2dexample.Systems.PhysicsDebugRendererSystem;
import com.ronscript.overlap2dexample.Systems.PhysicsSystem;
import com.ronscript.overlap2dexample.Systems.InputSystem;
import com.ronscript.overlap2dexample.Systems.CharacterSystem;
import com.ronscript.overlap2dexample.Systems.RenderingSystem;
import com.ronscript.overlap2dexample.Systems.StateMachineSystem;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.utils.Constants;

/**
 * Created by Ron on 6/16/2016.
 */
public class GameWorld implements Disposable {

    private PooledEngine engine;
    private World world;
    private BodyManager bodyManager;
    private EntityBuilder factory;

    public GameWorld() {
        world = new World(Constants.GRAVITY, true);
        bodyManager = new BodyManager(world);
        engine = new PooledEngine();
        factory = new EntityBuilder(world, engine);
        world.setContactListener(new WorldContactListener(factory));
        setupEngine();
    }

    public void buildWorld() {
        factory.build();
    }

    public Camera getCamera() {
        return engine.getSystem(RenderingSystem.class).getCamera();
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public void setProcessing(boolean processing) {
        for (EntitySystem es : engine.getSystems()) {
            es.setProcessing(processing);
        }
    }

    private void setupEngine() {
        addSystems();
        addEntityListeners();
    }

    private void addSystems() {
        engine.addSystem(new CharacterSystem());
        engine.addSystem(new MovementSystem());

        engine.addSystem(new CameraSystem());
        engine.addSystem(new AnimationSystem()); // Animation System fills the TextureComponent textureregion

        engine.addSystem(new RenderingSystem()); // draw the sprite class
        engine.addSystem(new PhysicsSystem(world, bodyManager));
        engine.addSystem(new PhysicsDebugRendererSystem(world));
        engine.getSystem(PhysicsDebugRendererSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());

        engine.addSystem(new GunSystem());
        engine.addSystem(new BulletSystem(factory));

        engine.addSystem(new InputSystem(engine.getSystem(RenderingSystem.class).getSpriteBath(), getCamera()));
        engine.addSystem(new StateMachineSystem());
        engine.addSystem(new NodeSystem());
    }

    private void addEntityListeners() {
        engine.addEntityListener(engine.getSystem(AnimationSystem.class).getFamily(), new AnimationSystemEntityListener());
        engine.addEntityListener(engine.getSystem(PhysicsSystem.class).getFamily(), new BodyRemovalListener(bodyManager));
    }

    @Override
    public void dispose() {
        for (EntitySystem entitySystem : engine.getSystems()) {
            if (entitySystem instanceof Disposable) {
                ((Disposable) entitySystem).dispose();
            }
            engine.removeSystem(entitySystem);
        }
    }
}
