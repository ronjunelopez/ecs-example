package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.ronscript.overlap2dexample.Components.CameraComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;

/**
 * Created by Ron on 6/25/2016.
 */
public class PhysicsDebugRendererSystem extends IteratingSystem implements Disposable {

    private Box2DDebugRenderer debugger;
    private World world;
    private Camera camera;

    public PhysicsDebugRendererSystem(World world) {
        super(Family.all(PhysicsComponent.class, CameraComponent.class).get());
        debugger = new Box2DDebugRenderer();
        this.world = world;
    }

    public Box2DDebugRenderer getDebugger(){
        return debugger;
    }

    public void setCamera(Camera camera){
        this.camera = camera;
    }

    @Override
    public void update(float deltaTime) {
        if(camera != null) {
            debugger.render(world, camera.combined);
        }
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {


    }

    @Override
    public void dispose() {
        debugger.dispose();
    }
}
