package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

/**
 * @author Ron
 * @since 7/16/2016
 */
public class NodeComponent implements Component {

    public Array<Entity> childs = new Array<Entity>();

}
