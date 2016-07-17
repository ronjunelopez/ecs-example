package com.ronscript.overlap2dexample.Listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.ronscript.overlap2dexample.Managers.BodyManager;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 7/1/2016.
 */
public class BodyRemovalListener implements EntityListener {

    BodyManager manager;

    public BodyRemovalListener(BodyManager manager) {
        this.manager = manager;
    }

    @Override
    public void entityAdded(Entity entity) {
//        Gdx.app.log("Entity "+entity.flags, "body added to update");
        manager.addBody(entity, Mappers.physics.get(entity).body);
    }

    @Override
    public void entityRemoved(Entity entity) {
//        Gdx.app.log("Entity "+entity.flags, "body added to remove");
        manager.removeBody(entity);
    }
}
