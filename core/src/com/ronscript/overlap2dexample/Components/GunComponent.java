package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ronscript.overlap2dexample.entities.Cannonball;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.entities.builders.PooledBullet;

/**
 * Created by Ron on 7/5/2016.
 */
public class GunComponent implements Component {

    public final float TRIGGER_SPEED = 6;


    public EntityBuilder factory;

    public final Array<PooledBullet> activeCannonball = new Array<PooledBullet>();
    public Pool<PooledBullet> cannonballPool = Pools.get(PooledBullet.class);


    public Cannonball cannonball;
    public Vector2 sights = new Vector2();
    public float time = 0;
    public int ammo = 10;

}
