package com.ronscript.overlap2dexample.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.states.GunState;

/**
 * @author Ron
 * @since 7/8/2016
 */
public class Gun{

    public Entity entity;
    /**
     *  Components
     */
    public GunComponent gun;
    public TextureComponent graphic;
    public SizeComponent size;
    public PhysicsComponent physics;
    public TransformComponent transform;
    public MovementComponent movement;
    public StateComponent state;
    public AIComponent ai;
    public NodeComponent node;

    public Gun() {

    }
    public void sights(Vector2 target) {
        gun.sights.set(target);
    }
    public void trigger() {
        ai.fsm.changeState(GunState.FIRE);
    }

}
