package com.ronscript.overlap2dexample.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Ron on 6/28/2016.
 */
public class WorldUtils {

    public static final float UNIT_PIXEL = 32.0f; // 32 pixel
    public static final float pixelsToMetres = 1 / UNIT_PIXEL; // 32 pixel = 1 meter (1/32)
    public static final float metresToPixels = UNIT_PIXEL;

    public static float frustum(float pixels){
        return pixels * pixelsToMetres;
    }

    /**
     *
     * @return Vector2 position in meters .
     */
    public static Vector2 touchpointMeters(Vector3 touchpoint) {
       return touchpointPixels(touchpoint).scl(WorldUtils.pixelsToMetres);
    }
    /**
     * Convert the Vector3 touchpoint to Vector2 to get the sights position
     * and scaled the touchpoint by UNIT_PIXEL to get the pixel value
     * @return Vector2 position in PIXELS.
     */
    public static Vector2 touchpointPixels(Vector3 touchpoint){
        Vector3 touchpointPixel = touchpoint.cpy().scl(UNIT_PIXEL);
        return new Vector2(touchpointPixel.x, touchpointPixel.y);
    }

}
