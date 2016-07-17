package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.World;
import com.ronscript.overlap2dexample.Components.CameraComponent;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.Systems.RenderingSystem;
import com.ronscript.overlap2dexample.entities.Gun;
import com.ronscript.overlap2dexample.entities.Player;
import com.ronscript.overlap2dexample.entities.Slime;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.WorldUtils;
/**
 * Created by Ron on 6/5/2016.
 */
public class EntityBuilder {

    // Ashley System variables
    private PooledEngine engine;
    // Physics world variables
    private World world;

    public EntityBuilder(World world, PooledEngine engine) {
        this.world = world;
        this.engine = engine;
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public World getWorld() {
        return world;
    }

    public void build() {
//        Bullet bullet = bulletPool.obtain();
//        bullet.init(new Vector2(50,50), new Vector2(0,0));
//        Entity gun = createGun(10,0);
        GunBuilder gunBuilder = new GunBuilder(this);
        gunBuilder.setSize(12 * WorldUtils.pixelsToMetres, 14 * WorldUtils.pixelsToMetres);
        gunBuilder.setTransform(0,0);
        gunBuilder.build();
        Gun gun = gunBuilder.getGun();
        gun.graphic.z = 1;


//        Entity character = createPlayer(1, 1, gun);

        PlayerBuilder playerBuilder = new PlayerBuilder(this);
        playerBuilder.setAnimation();
        playerBuilder.setWeapon(gun);
        playerBuilder.setSize(1,1);
        playerBuilder.setTransformation(0,0);
        playerBuilder.setOriginCenter();
        playerBuilder.setBody(playerBuilder.createBody());
        playerBuilder.build();

        Player player = playerBuilder.getPlayer();

//        Entity dummyPlayer = createPlayer(33,33);
//        dummyPlayer.remove(PlayerControlledComponent.class);
//        Entity player2 = createPlayer(64,64);
        float counter = 0;

        CoinBuilder coinBuilder = new CoinBuilder(this);

        for (int i = 0; i < 10; i++) {
            coinBuilder.createEntity();
            coinBuilder.createComponents();
//            coinBuilder.setTexture(GameAssets.coin.getKeyFrame(0));
            coinBuilder.putAnimation(0, GameAssets.coin);
            coinBuilder.setBounds(counter, 1,0.5f, 0.5f);
            coinBuilder.setOriginCenter();
            coinBuilder.setBody(coinBuilder.createBody());
            coinBuilder.build();
            com.ronscript.overlap2dexample.entities.Coin coin = coinBuilder.getCoin();
            counter += 0.5f;
        }

        new Slime(this, 0, 4);
        new Slime(this, 1, 4);
        new Slime(this, 2, 4);
        new Slime(this, 3, 4);
        createCamera(player.entity);

    }

    private Entity createCamera(Entity target) {
        Entity cam = createEntity(Constants.Flags.CAMERA);
        CameraComponent camera = engine.createComponent(CameraComponent.class);
        camera.camera = engine.getSystem(RenderingSystem.class).getCamera();
        camera.target = target;
        cam.add(camera);
        engine.addEntity(cam);
        return cam;
    }

    public Entity createEntity(Constants.Flags flag) {
        Entity e = engine.createEntity();
        e.flags = flag.ordinal();
        return e;
    }

    public Entity getEntity(int flags) {
        Entity entity = null;
        for (int i = 0; i < engine.getEntities().size(); ++i) {
            if (engine.getEntities().get(i).flags == flags) {
                entity = engine.getEntities().get(i);
            }
        }
        return entity;
    }

    public void removeEntity(Entity entity) {
        engine.removeEntity(entity);
    }
}
