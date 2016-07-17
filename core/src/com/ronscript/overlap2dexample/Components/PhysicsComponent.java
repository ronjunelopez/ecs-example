package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Ron on 6/17/2016.
 */
public class PhysicsComponent implements Component {
    private World world;
    public Body body;

    public boolean registered = false;

    public void registerWorld(World world){
        if(world != null && !registered) {
            this.world = world;
            registered = true;
        }
    }

    public World getWorld() throws Exception {
        if(!registered){
            throw new Exception("Physics World is not registered");
        }
        return world;
    }
}
