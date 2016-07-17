package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.gdx.physics.box2d.Body;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;

/**
 * @author Ron
 * @since 7/17/2016
 */
public abstract class PhysicalAnimatedSpriteBuilder extends AnimatedSpriteBuilder implements Physical{

    private PhysicsComponent physicsComponent;

    public PhysicalAnimatedSpriteBuilder() {

    }

    @Override
    public PhysicsComponent getPhysicsComponent() {
        return physicsComponent;
    }

    @Override
    public void setPhysicsComponent(PhysicsComponent physicsComponent) {
        this.physicsComponent = physicsComponent;
    }

    @Override
    public Body getBody() {
        return physicsComponent.body;
    }

    @Override
    public void setBody(Body body) {
        physicsComponent.body = body;
    }

}
