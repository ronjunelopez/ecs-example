package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * @author Ron
 * @since 7/12/2016
 */
public class StateMachineSystem extends IteratingSystem {

    public StateMachineSystem() {
        super(Family.all(AIComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AIComponent ai = Mappers.stateMachine.get(entity);
        ai.stateMachine.update();
    }
}
