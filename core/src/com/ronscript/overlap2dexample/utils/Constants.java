package com.ronscript.overlap2dexample.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ron on 6/28/2016.
 */
public class Constants {

    public static final float WIDTH = 400;
    public static final float HEIGHT = 208;
    public static final float FRUSTUM_WIDTH = WorldUtils.frustum(WIDTH);
    public static final float FRUSTUM_HEIGHT = WorldUtils.frustum(HEIGHT);

    public static final Vector2 GRAVITY = new Vector2(0,0);

    public static final float FRAMES = 60.0f;
    public static final float TIME_STEP = 1 / FRAMES;
    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    public enum Flags {
        DEFAULT,
        PLAYER,
        SLIME,
        COIN,
        WEAPON,
        BULLET,
        CAMERA
    }

    // Collision filtering stuff
    public static final short CATEGORY_PLAYER = 0x0001;
    public static final short CATEGORY_ENEMY = 0x0002;
    public static final short CATEGORY_ITEM = 0x0004;
    public static final short CATEGORY_GROUND = 0x0008;
    public static final short CATEGORY_SENSOR = 0x00016;
    public static final short CATEGORY_BULLET = 0x00032;
    public static final short CATEGORY_GUN = 0x00064;

    public static final short MASK_PLAYER = ~CATEGORY_PLAYER; // Cannot collide with character objects
    public static final short MASK_ENEMY =  ~CATEGORY_ENEMY; // Cannot collide with enemy objects
    public static final short MASK_ITEM =  ~CATEGORY_ITEM; // Cannot collide with enemy objects
    public static final short MASK_SENSOR =  CATEGORY_PLAYER; // Can collide only with players
    public static final short MASK_GROUND = -1; // Can collide with everything
    public static final short MASK_BULLET = CATEGORY_ENEMY | CATEGORY_GROUND;
    public static final short MASK_GUN = CATEGORY_ITEM;

}
