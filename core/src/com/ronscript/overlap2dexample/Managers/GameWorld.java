package com.ronscript.overlap2dexample.Managers;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.ronscript.overlap2dexample.Listeners.AnimationSystemEntityListener;
import com.ronscript.overlap2dexample.Listeners.BodyRemovalListener;
import com.ronscript.overlap2dexample.Listeners.WorldContactListener;
import com.ronscript.overlap2dexample.Systems.AISystem;
import com.ronscript.overlap2dexample.Systems.AnimationSystem;
import com.ronscript.overlap2dexample.Systems.BulletSystem;
import com.ronscript.overlap2dexample.Systems.CameraSystem;
import com.ronscript.overlap2dexample.Systems.CharacterSystem;
import com.ronscript.overlap2dexample.Systems.GunSystem;
import com.ronscript.overlap2dexample.Systems.InputSystem;
import com.ronscript.overlap2dexample.Systems.MovementSystem;
import com.ronscript.overlap2dexample.Systems.NodeSystem;
import com.ronscript.overlap2dexample.Systems.PhysicsDebugRendererSystem;
import com.ronscript.overlap2dexample.Systems.PhysicsSystem;
import com.ronscript.overlap2dexample.Systems.RenderingSystem;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.MapBodyManager;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * Created by Ron on 6/16/2016.
 */
public class GameWorld implements Disposable {

    private SpriteBatch batch;

    private PooledEngine engine;
    private World world;
    private BodyManager bodyManager;
    private EntityBuilder factory;

    //Tiled map variables
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public GameWorld(SpriteBatch batch) {
        this.batch = batch;
        world = new World(Constants.GRAVITY, true);
        bodyManager = new BodyManager(world);
        engine = new PooledEngine();
        factory = EntityBuilder.getInstance();
        factory.setEngine(engine);
        factory.setWorld(world);
        world.setContactListener(new WorldContactListener(factory));

        TmxMapLoader maploader = new TmxMapLoader();
        tiledMap = maploader.load("maps/world.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, WorldUtils.pixelsToMetres, batch);
        MapBodyManager mapBodyManager = new MapBodyManager(world, WorldUtils.UNITS_PIXEL);
        mapBodyManager.createPhysics(tiledMap, "wall");
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

        engine.addSystem(new RenderingSystem(batch, tiledMapRenderer)); // draw the sprite class
        engine.addSystem(new PhysicsSystem(world, bodyManager));
        engine.addSystem(new PhysicsDebugRendererSystem(world));
        engine.getSystem(PhysicsDebugRendererSystem.class).setCamera(engine.getSystem(RenderingSystem.class).getCamera());

        engine.addSystem(new GunSystem(factory));
        engine.addSystem(new BulletSystem(factory));

        engine.addSystem(new InputSystem(engine.getSystem(RenderingSystem.class).getSpriteBath(), getCamera()));
        engine.addSystem(new AISystem());
        engine.addSystem(new NodeSystem());
    }

    private void addEntityListeners() {
        engine.addEntityListener(engine.getSystem(AnimationSystem.class).getFamily(), new AnimationSystemEntityListener());
        engine.addEntityListener(engine.getSystem(PhysicsSystem.class).getFamily(), new BodyRemovalListener(bodyManager));
    }

    @Override
    public void dispose() {
        setProcessing(false);
        engine.removeAllEntities();
        for (EntitySystem entitySystem : engine.getSystems()) {
            if (entitySystem instanceof Disposable) {
                ((Disposable) entitySystem).dispose();
            }
            engine.removeSystem(entitySystem);
        }
    }
}
