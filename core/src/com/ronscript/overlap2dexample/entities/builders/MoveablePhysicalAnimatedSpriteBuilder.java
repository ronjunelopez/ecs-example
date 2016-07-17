package com.ronscript.overlap2dexample.entities.builders;

import com.ronscript.overlap2dexample.Components.MovementComponent;

/**
 * @author Ron
 * @since 7/17/2016
 */
public abstract class MoveablePhysicalAnimatedSpriteBuilder extends PhysicalAnimatedSpriteBuilder implements Moveable {

    private MovementComponent movementComponent;

    @Override
    public MovementComponent getMovementComponent() {
        return movementComponent;
    }

    @Override
    public void setMovementComponent(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }
}
