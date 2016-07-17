package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.ronscript.overlap2dexample.Components.CameraComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/25/2016.
 */
public class CameraSystem extends IteratingSystem {

    public CameraSystem() {
        super(Family.all(CameraComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        CameraComponent camera = Mappers.camera.get(entity);

        if(camera.camera != null) {
                Entity target = camera.target;
                TransformComponent transform = Mappers.transform.get(target);
                SizeComponent size = Mappers.size.get(target);

            if(transform != null){
                float bounceX = 0;
                float bounceY = 0;

                float frustumX = transform.position.x ;
                float frustumY = transform.position.y;

                bounceX = Interpolation.bounceIn.apply(camera.camera.position.x, frustumX, 0.1f);
                bounceY = Interpolation.bounceIn.apply(camera.camera.position.y, frustumY, 0.1f);

                camera.camera.position.x = bounceX;
                camera.camera.position.y = bounceY;


           } else {
               Gdx.app.log("Camera", "Target is missing");
           }
        }
    }


}
