package com.ronscript.overlap2dexample.entities;

import com.badlogic.ashley.core.Entity;

/**
 * @author Ron
 * @since 7/16/2016
 */
public class Coin{

    private Entity entity;

    public Coin(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
