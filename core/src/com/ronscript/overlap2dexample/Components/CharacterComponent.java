package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ron on 6/17/2016.
 */
public class CharacterComponent implements Component {

    public Vector2 target = new Vector2();
    public boolean attack;
    public boolean deadend = false;

    public final float JUMP_VELOCITY = 1;
    public final float MOVE_VELOCITY = 6;
}
