package com.ronscript.overlap2dexample.Listeners;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * Created by Ron on 7/3/2016.
 */
public class PlayerInputAdapter extends InputAdapter {


    private final Camera camera;

    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;
    private boolean pressed = false;
    private boolean touched = false;


    private Vector3 touchpoint = new Vector3();

    public PlayerInputAdapter(Camera camera) {
        this.camera = camera;
    }

    @Override
    public boolean keyDown(int keycode) {
        pressed = true;
        switch (keycode){
            case Input.Keys.W:
                keyUp = true;
                break;
            case Input.Keys.A:
                keyLeft = true;
                break;
            case Input.Keys.S:
                keyDown = true;
                break;
            case Input.Keys.D:
                keyRight = true;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        pressed = false;
        switch (keycode){
            case Input.Keys.W:
                keyUp = false;
                break;
            case Input.Keys.A:
                keyLeft = false;
                break;
            case Input.Keys.S:
                keyDown = false;
                break;
            case Input.Keys.D:
                keyRight = false;
                break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;

        // This will make the touchpoint point get the position of world and not the camera screen
        camera.unproject(touchpoint.set(screenX, screenY, 0));
        touched = true;
//                dragging = true;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        camera.unproject(touchpoint.set(screenX, screenY, 0));
        touched = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (!touched) return false;
        camera.unproject(touchpoint.set(screenX, screenY, 0));
        return true;
    }

    public Vector3 getTouchpoint(){
        return touchpoint;
    }

    public Vector2 getGameTouchpointInMeters() {
        return WorldUtils.touchpointMeters(touchpoint);
    }

    public Vector2 getGameTouchpointInPixels() {
        return WorldUtils.touchpointPixels(touchpoint);
    }

    public boolean isKeyUp() {
        return keyUp;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public boolean isKeyLeft() {
        return keyLeft;
    }

    public boolean isKeyRight() {
        return keyRight;
    }

    public boolean isPressed() {
        return pressed;
    }

    public boolean isTouched(){
        return touched;
    }
}
