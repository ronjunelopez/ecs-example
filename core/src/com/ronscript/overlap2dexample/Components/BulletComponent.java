package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ron on 7/3/2016.
 */
public class BulletComponent implements Component{

    public boolean alive = true;
    public boolean fired = false;
    public final Vector2 destination = new Vector2();
}
