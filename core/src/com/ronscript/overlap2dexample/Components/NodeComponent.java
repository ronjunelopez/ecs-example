package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;

import java.util.Iterator;

/**
 * @author Ron
 * @since 7/16/2016
 */
public class NodeComponent implements Component {

    private final ArrayMap<String, Entity> children = new ArrayMap<String, Entity>();
    public int index = 0;

    public void addChild(Entity entity) {
        addChild(String.valueOf(index), entity);
        index++;
    }

    public Iterator<ObjectMap.Entry<String, Entity>> getChildren() {
        return children.iterator();
    }

    public void addChild(String childKey, Entity childVal) {
        children.put(childKey, childVal);
    }

    public void removeChild(String childKey) {
        children.removeKey(childKey);
    }

    public boolean hasChild(String childKey) {
        return children.containsKey(childKey);
    }

    public Entity getChild(String childKey) {
        return children.get(childKey);
    }

}
