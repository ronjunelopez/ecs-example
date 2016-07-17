package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;

/**
 * Created by Ron on 6/25/2016.
 */
public class CameraComponent implements Component {
    public Camera camera;
    public Entity target;
}
