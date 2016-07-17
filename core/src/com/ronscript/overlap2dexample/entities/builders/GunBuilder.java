package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.entities.Gun;
import com.ronscript.overlap2dexample.utils.Constants;

/**
 * @author Ron
 * @since 7/14/2016
 */
public class GunBuilder {

    private PooledEngine engine;
    private Gun gun;

    public GunBuilder(EntityBuilder factory) {
        engine = factory.getEngine();

        gun = new Gun();
        gun.entity = factory.createEntity(Constants.Flags.GUN);
        gun.ai = engine.createComponent(AIComponent.class);
        gun.gun = engine.createComponent(GunComponent.class);
        gun.graphic = engine.createComponent(TextureComponent.class);
        gun.size = engine.createComponent(SizeComponent.class);
        gun.transform = engine.createComponent(TransformComponent.class);
        gun.movement = engine.createComponent(MovementComponent.class);
        gun.state = engine.createComponent(StateComponent.class);
        gun.ai.stateMachine = new DefaultStateMachine<Gun, com.ronscript.overlap2dexample.entities.states.GunState>(gun, com.ronscript.overlap2dexample.entities.states.GunState.RELOAD);
        gun.state.setState(MovementComponent.State.IDLE.ordinal());
        gun.gun.factory = factory;
        gun.graphic.region = new TextureRegion(GameAssets.cannon_down);
    }

    public void setTransform(float posX, float posY) {
        gun.transform.position.set(posX, posY);
    }

    public void setSize(float width, float height) {
        gun.size.width = width;
        gun.size.height = height;
    }

    public TextureRegion getTextureRegion() {
        return gun.graphic.region;
    }

    public void build() {
        gun.entity.add(gun.ai);
        gun.entity.add(gun.gun);
        gun.entity.add(gun.graphic);
        gun.entity.add(gun.size);
        gun.entity.add(gun.transform);
        gun.entity.add(gun.movement);
        gun.entity.add(gun.state);
        gun.entity.add(gun.ai);

        engine.addEntity(gun.entity);
    }
    public Gun getGun() {
        return gun;
    }

}
