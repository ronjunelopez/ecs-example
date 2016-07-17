package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by Ron on 6/13/2016.
 */
public class AnimationComponent implements Component {
    public IntMap<Animation> map = new IntMap<Animation>();
    public boolean isLooping = false;
}
