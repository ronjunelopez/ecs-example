package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectMap;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.utils.Mappers;

import java.util.Iterator;

/**
 * @author Ron
 * @since 7/16/2016
 */
public class NodeSystem extends IteratingSystem {

    public NodeSystem() {
        super(Family.all(NodeComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        transformNode(entity, deltaTime);
    }

    private void transformNode(Entity entity, float deltaTime) {
        TransformComponent parentTransform = Mappers.transform.get(entity);
        MovementComponent parentMovement = Mappers.movement.get(entity);
        PhysicsComponent parentPhysics = Mappers.physics.get(entity);

        NodeComponent parentNode = Mappers.node.get(entity);
        Iterator<ObjectMap.Entry<String, Entity>> children = parentNode.getChildren();
        while (children.hasNext()) {
            ObjectMap.Entry<String, Entity> child = children.next();
            TransformComponent childTransform = Mappers.transform.get(child.value);
            MovementComponent childMovement = Mappers.movement.get(child.value);
            PhysicsComponent childPhysics = Mappers.physics.get(child.value);

            if (childTransform == null) {
                return;
            }
//            childMovement.state = parentMovement.state; @TODO dont forget to uncomment and change state to statemachine
            if (childPhysics != null && parentPhysics != null) {
                childPhysics.body.setTransform(parentPhysics.body.getPosition(), parentPhysics.body.getAngle());
            } else if (childPhysics != null && parentPhysics == null) {
                float parentBodyPosX = parentTransform.position.x + parentTransform.origin.x;
                float parentBodyPosY = parentTransform.position.y + parentTransform.origin.y;
                childPhysics.body.setTransform(new Vector2(parentBodyPosX, parentBodyPosY), parentTransform.rotation); // @TODO parentTransform.ratation must be converted to angle
            } else {
                childMovement.velocity.set(parentMovement.velocity);
                childTransform.position.set(parentTransform.position);
            }
        }
    }
}
