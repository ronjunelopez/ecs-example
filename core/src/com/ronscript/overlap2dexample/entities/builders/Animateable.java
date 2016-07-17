package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;

/**
 * @author Ron
 * @since 7/17/2016
 */
public interface Animateable {
    AnimationComponent getAnimationComponent();
    StateComponent getStateComponent();
    void setAnimationComponent(AnimationComponent animationComponent);
    void setStateComponent(StateComponent stateComponent);


    IntMap<Animation> getAnimations();
    void putAnimation(int key, Animation animation);
}
