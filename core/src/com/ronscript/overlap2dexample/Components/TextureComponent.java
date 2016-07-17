package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ron on 6/13/2016.
 */
public class TextureComponent implements Component {
    public TextureRegion region = new TextureRegion();
    public int z = 0;
}
