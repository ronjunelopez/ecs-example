package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ron on 6/13/2016.
 */
public class TransformComponent implements Component {
    public final Vector2 origin = new Vector2();
    public final Vector2 position = new Vector2();
    public final Vector2 scale = new Vector2(1, 1);
    public float rotation = 0.0f;
}
