package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.entities.Cannonball;

/**
 * Created by Ron on 7/5/2016.
 */
public class GunComponent implements Component {

    public final float TRIGGER_SPEED = 16;
    public final Array<Cannonball> activeCannonball = new Array<Cannonball>();
    public EntityBuilder factory;
    public Pool<Cannonball> cannonballPool = Pools.get(Cannonball.class);
    public Cannonball cannonball;
    public Vector2 sights = new Vector2();
    public float time = 0;
    public int ammo = 10;

}
