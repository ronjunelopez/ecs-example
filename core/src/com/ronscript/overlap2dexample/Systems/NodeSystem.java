package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * @author Ron
 * @since 7/16/2016
 */
public class NodeSystem extends IteratingSystem {

    public NodeSystem() {
        super(Family.all(NodeComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        transformNode(entity);
    }

    private void transformNode(Entity entity) {
        TransformComponent parentTransform = Mappers.transform.get(entity);
        NodeComponent parentNode = Mappers.node.get(entity);
        for (Entity child: parentNode.childs){
            TransformComponent childTransform = Mappers.transform.get(child);
            childTransform.position.set(parentTransform.position);
        }
    }
}
