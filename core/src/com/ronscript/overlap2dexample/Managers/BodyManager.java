package com.ronscript.overlap2dexample.Managers;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

/**
 * Created by Ron on 6/30/2016.
 */
public class BodyManager {

    private final World world;
    private ArrayMap<Object, Body> tempBodies;
    private Array<Body> bodiesToDestroy;

    public BodyManager(World world) {
        this.world = world;
        tempBodies = new ArrayMap<Object, Body>();
        bodiesToDestroy = new Array<Body>();
    }

    public void addBody(Object userdata, Body body){
        tempBodies.put(userdata, body);
    }

    public void removeBody(Object userdata){
        bodiesToDestroy.add(tempBodies.get(userdata));
    }

    public void destroyBodies(){
        for (Body body : bodiesToDestroy){
            world.destroyBody(body);
            bodiesToDestroy.removeValue(body, true);
        }
    }

}
