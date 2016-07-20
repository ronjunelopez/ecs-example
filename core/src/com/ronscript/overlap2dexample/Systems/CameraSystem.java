package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.ronscript.overlap2dexample.Components.CameraComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
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
        TransformComponent cameraTransform = Mappers.transform.get(entity);

        if(camera.camera != null) {
                Entity target = camera.target;
                TransformComponent transform = Mappers.transform.get(target);
            MovementComponent movement = Mappers.movement.get(target);
                SizeComponent size = Mappers.size.get(target);

            if(transform != null){
                float bounceX = 0;
                float bounceY = 0;

                bounceX = Interpolation.bounceIn.apply(camera.camera.position.x, transform.position.x, 0.1f);
                bounceY = Interpolation.bounceIn.apply(camera.camera.position.y, transform.position.y, 0.1f);
                cameraTransform.position.set(bounceX, bounceY);

                camera.camera.position.x = cameraTransform.position.x;
                camera.camera.position.y = cameraTransform.position.y;


           } else {
               Gdx.app.log("Camera", "Target is missing");
           }
        }
    }


}
