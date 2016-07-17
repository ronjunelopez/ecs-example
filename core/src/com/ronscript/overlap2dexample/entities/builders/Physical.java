package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.gdx.physics.box2d.Body;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;

/**
 * @author Ron
 * @since 7/17/2016
 */
public interface Physical {

    PhysicsComponent getPhysicsComponent();
    void setPhysicsComponent(PhysicsComponent physicsComponent);

    Body getBody();
    void setBody(Body body);
}
