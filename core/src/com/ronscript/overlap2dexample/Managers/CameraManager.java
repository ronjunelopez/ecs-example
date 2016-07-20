package com.ronscript.overlap2dexample.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * @author Ron
 * @since 7/8/2016
 */
public class CameraManager {
    private static CameraManager ourInstance = new CameraManager();

    public static CameraManager getInstance() {
        return ourInstance;
    }

    private OrthographicCamera camera;
    public boolean debugmode = false;

    private CameraManager() {
    }

    public boolean isOutsideCamera(Vector2 position){
        return isOutsideCamera(position, 0,0);
    }

    public boolean isOutsideCamera(Vector2 position, float offset){
        return isOutsideCamera(position, offset, offset);
    }

    public boolean isOutsideCamera(Vector2 position, float offsetX, float offsetY) {
        boolean isOutside = false;
        float CAMERA_WIDTH = Constants.FRUSTUM_WIDTH;
        float CAMERA_HEIGHT = Constants.FRUSTUM_HEIGHT;

        OrthographicCamera camera = CameraManager.getInstance().getCamera();
        Vector3 camPos = camera.position;
        if(position.x > camPos.x + (CAMERA_WIDTH + (offsetX * WorldUtils.pixelsToMetres)) / 2
                || position.x < camPos.x - (CAMERA_WIDTH + (offsetX * WorldUtils.pixelsToMetres)) / 2
                || position.y  > camPos.y + (CAMERA_HEIGHT + (offsetY * WorldUtils.pixelsToMetres)) / 2
                || position.y  < camPos.y - (CAMERA_HEIGHT + (offsetY * WorldUtils.pixelsToMetres)) / 2  ) {
            if (debugmode) {
                Gdx.app.log("Outside", position.toString());
            }
            isOutside = true;
        }
        return isOutside;
    }

    public void setupCamera(){
        camera = new OrthographicCamera();
    }

    public OrthographicCamera getCamera(){
        return  camera;
    }
}
