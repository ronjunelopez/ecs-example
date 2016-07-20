package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Ron on 7/1/2016.
 */
public class InputComponent implements Component {

    public boolean keyUp = false;
    public boolean keyDown = false;
    public boolean keyLeft = false;
    public boolean keyRight = false;
    public boolean pressed = false;
    public boolean touched = false;
    public final Vector3 touchpoint = new Vector3();

}
