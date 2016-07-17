package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/13/2016.
 */
public class AnimationSystem extends IteratingSystem {

    public AnimationSystem() {
        super(Mappers.animationFamily);
    }

    @Override
    public void processEntity(Entity entity, float deltaTime) {
        TextureComponent render = Mappers.texture.get(entity);
        AnimationComponent animation = Mappers.animation.get(entity);
        StateComponent state = Mappers.state.get(entity);
        if(state != null && render != null && animation != null ) {
            if (animation.map.containsKey(state.getState())) {
                Animation a = animation.map.get(state.getState());
                render.region = a.getKeyFrame(state.time, animation.isLooping);
            }
            state.time += deltaTime;
        }
    }
}
