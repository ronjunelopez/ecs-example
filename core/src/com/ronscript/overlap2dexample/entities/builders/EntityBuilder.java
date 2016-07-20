package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.Components.CameraComponent;
import com.ronscript.overlap2dexample.Components.CharacterComponent;
import com.ronscript.overlap2dexample.Components.InputComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.Systems.RenderingSystem;
import com.ronscript.overlap2dexample.entities.Coin;
import com.ronscript.overlap2dexample.entities.Gun;
import com.ronscript.overlap2dexample.entities.Slime;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.WorldUtils;
/**
 * Created by Ron on 6/5/2016.
 */
public class EntityBuilder {
    public static EntityBuilder instance = new EntityBuilder();
    // Ashley System variables
    private PooledEngine engine;
    // Physics world variables
    private World world;
    private boolean built = false;

    public EntityBuilder() {

    }

    public EntityBuilder(World world, PooledEngine engine) {
        this.setWorld(world);
        this.setEngine(engine);
    }

    public static EntityBuilder getInstance() {
        if (instance == null) {
            instance = new EntityBuilder();
        }
        return instance;
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public World getWorld() {
        return world;
    }

    public void build() {
        if (built)
            return;
        built = true;
//        Bullet bullet = bulletPool.obtain();
//        bullet.init(new Vector2(50,50), new Vector2(0,0));
//        Entity gun = createGun(10,0);
        GunBuilder gunBuilder = new GunBuilder(this);
        gunBuilder.setSize(12 * WorldUtils.pixelsToMetres, 14 * WorldUtils.pixelsToMetres);
        gunBuilder.setTransform(0,0);
        gunBuilder.build();
        Gun gun = gunBuilder.getGun();
        gun.graphic.z = 1;

        Entity character = createCharacter(0, 0, gun.entity);

        float counter = 0;

        CoinBuilder coinBuilder = new CoinBuilder(this);

        for (int i = 0; i < 10; i++) {
            coinBuilder.createEntity();
            coinBuilder.createComponents();
//            coinBuilder.setTexture(GameAssets.coin.getKeyFrame(0));
            coinBuilder.putAnimation(0, GameAssets.coin);
            coinBuilder.setBounds(counter, 3, 0.5f, 0.5f);
            coinBuilder.setOriginCenter();
            coinBuilder.setBody(coinBuilder.createBody());
            coinBuilder.build();
            Coin coin = coinBuilder.getCoin();
            counter += 0.5f;
        }

        new Slime(this, 0, 4);
        new Slime(this, 1, 4);
        new Slime(this, 2, 4);
        new Slime(this, 3, 4);

        createCamera(character);
    }


    private Entity createCamera(Entity target) {
        Entity cam = createEntity(Constants.Flags.CAMERA);
        CameraComponent camera = engine.createComponent(CameraComponent.class);
        MovementComponent movement = engine.createComponent(MovementComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);

        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;

        cam.add(movement);
        cam.add(transform);
        cam.add(camera);
        engine.addEntity(cam);
        return cam;
    }

    public Entity createCharacter(float x, float y, Entity weapon) {
        Entity entity = createEntity(Constants.Flags.PLAYER);
        CharacterComponent character = getEngine().createComponent(CharacterComponent.class);
        TextureComponent texture = getEngine().createComponent(TextureComponent.class);
        SizeComponent size = getEngine().createComponent(SizeComponent.class);
        StateComponent state = getEngine().createComponent(StateComponent.class);
        AnimationComponent animation = getEngine().createComponent(AnimationComponent.class);
        TransformComponent transform = getEngine().createComponent(TransformComponent.class);
        PhysicsComponent physics = getEngine().createComponent(PhysicsComponent.class);
        MovementComponent movement = getEngine().createComponent(MovementComponent.class);
        InputComponent input = getEngine().createComponent(InputComponent.class);

        NodeComponent node = getEngine().createComponent(NodeComponent.class);
        node.addChild("weapon", weapon);

        texture.region = GameAssets.character_idle;
        animation.map.put(MovementComponent.Direction.IDLE.ordinal(), GameAssets.character_idle2);
        animation.map.put(MovementComponent.Direction.SOUTH.ordinal(), GameAssets.character_south);
        animation.map.put(MovementComponent.Direction.NORTH.ordinal(), GameAssets.character_north);
        animation.map.put(MovementComponent.Direction.WEST.ordinal(), GameAssets.character_right_side);
        animation.map.put(MovementComponent.Direction.EAST.ordinal(), GameAssets.character_left_side);
        animation.map.put(MovementComponent.Direction.NORTH_WEST.ordinal(), GameAssets.character_diag_right_up);
        animation.map.put(MovementComponent.Direction.NORTH_EAST.ordinal(), GameAssets.character_diag_left_up);
        animation.map.put(MovementComponent.Direction.SOUTH_WEST.ordinal(), GameAssets.character_diag_right_down);
        animation.map.put(MovementComponent.Direction.SOUTH_EAST.ordinal(), GameAssets.character_diag_left_down);
        state.setState(MovementComponent.Direction.IDLE.ordinal());

        transform.position.set(x, y);
        size.width = 1;
        size.height = 1;
        transform.origin.set(size.width / 2, size.height / 2);
        physics.body = createCharacterBody(entity, transform.position.x, transform.position.y);

        entity.add(size);
        entity.add(character);
        entity.add(state);
        entity.add(texture);
        entity.add(animation);
        entity.add(transform);
        entity.add(movement);
        entity.add(physics);
        entity.add(input);

        entity.add(node);

        getEngine().addEntity(entity);

        return entity;
    }

    public Body createCharacterBody(Object userdata, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = Constants.MASK_PLAYER;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(12 * WorldUtils.pixelsToMetres);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef).setUserData(userdata);
        circleShape.dispose();
        return body;
    }


    public Entity createBullet(float x, float y) {
        Entity entity = createEntity(Constants.Flags.BULLET);

        BulletComponent bullet = getEngine().createComponent(BulletComponent.class);
        SizeComponent size = getEngine().createComponent(SizeComponent.class);
        TextureComponent texture = getEngine().createComponent(TextureComponent.class);
        PhysicsComponent physics = getEngine().createComponent(PhysicsComponent.class);
        TransformComponent transform = getEngine().createComponent(TransformComponent.class);

        texture.region = GameAssets.cannonball;
        transform.position.set(x, y);
        size.width = 0.25f;
        size.height = 0.25f;
        transform.origin.set(size.width / 2, size.height / 2);
        physics.body = createBulletBody(entity, x, y);

        entity.add(bullet);
        entity.add(size);
        entity.add(texture);
        entity.add(physics);
        entity.add(transform);

        getEngine().addEntity(entity);
        return entity;
    }

    public Body createBulletBody(Object userdata, float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
//        bodyDef.active = false;

        Body body = getWorld().createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(6 * WorldUtils.pixelsToMetres);

        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_BULLET;
        fixtureDef.filter.maskBits = Constants.MASK_BULLET;
        fixtureDef.shape = circle;
        fixtureDef.density = 0.8f;
        fixtureDef.restitution = 0.2f;
        fixtureDef.friction = 0.99f;

        body.createFixture(fixtureDef).setUserData(userdata);

        circle.dispose();
        return body;
    }

    public Entity createEntity(Constants.Flags flag) {
        Entity e = getEngine().createEntity();
        e.flags = flag.ordinal();
        return e;
    }

    public Entity getEntity(int flags) {
        Entity entity = null;
        for (int i = 0; i < getEngine().getEntities().size(); ++i) {
            if (getEngine().getEntities().get(i).flags == flags) {
                entity = getEngine().getEntities().get(i);
            }
        }
        return entity;
    }

    public void removeEntity(Entity entity) {
        getEngine().removeEntity(entity);
    }

    public void setEngine(PooledEngine engine) {
        this.engine = engine;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
