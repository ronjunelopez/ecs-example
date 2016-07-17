package com.ronscript.overlap2dexample.entities.builders;

import com.ronscript.overlap2dexample.Components.MovementComponent;

/**
 * @author Ron
 * @since 7/17/2016
 */
public interface Moveable {

    MovementComponent getMovementComponent();
    void setMovementComponent(MovementComponent movementComponent);
}
