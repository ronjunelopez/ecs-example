package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;

/**
 * @author Ron
 * @since 7/17/2016
 */
public abstract class AnimatedSpriteBuilder extends SpriteBuilder implements Animateable{

    private AnimationComponent animationComponent;
    private StateComponent stateComponent;

    public AnimatedSpriteBuilder() {

    }

    @Override
    public AnimationComponent getAnimationComponent() {
        return animationComponent;
    }

    @Override
    public StateComponent getStateComponent() {
        return stateComponent;
    }

    @Override
    public void setAnimationComponent(AnimationComponent animationComponent) {
        this.animationComponent = animationComponent;
    }

    @Override
    public IntMap<Animation> getAnimations() {
        return animationComponent.map;
    }

    @Override
    public void putAnimation(int key, Animation animation) {
        animationComponent.map.put(key, animation);
    }

    @Override
    public void setStateComponent(StateComponent stateComponent) {
        this.stateComponent = stateComponent;
    }
}
