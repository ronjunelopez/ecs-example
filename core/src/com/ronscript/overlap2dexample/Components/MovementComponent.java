package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ron on 6/13/2016.
 */
public class MovementComponent implements Component {


    public final Vector2 accel = new Vector2();
    public final Vector2 velocity = new Vector2();

    public enum State {
        IDLE, NORTH,SOUTH,EAST,WEST, NORTH_WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST
    }

    public State state = State.IDLE;

}
