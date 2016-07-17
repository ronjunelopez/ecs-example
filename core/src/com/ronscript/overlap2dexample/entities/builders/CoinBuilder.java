package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.Coin;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * @author Ron
 * @since 7/16/2016
 */
public class CoinBuilder extends PhysicalAnimatedSpriteBuilder {

    Coin coin;
    final EntityBuilder factory;

    public CoinBuilder(EntityBuilder factory) {
       this.factory = factory;
    }
    public void createEntity() {
        final Entity entity = factory.createEntity(Constants.Flags.COIN);
        coin = new Coin(entity);
    }

    public void createComponents() {
        final PooledEngine engine = factory.getEngine();
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        SizeComponent size = engine.createComponent(SizeComponent.class);
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        StateComponent state = engine.createComponent(StateComponent.class);
        PhysicsComponent physics = engine.createComponent(PhysicsComponent.class);

        setTextureComponent(texture);
        setSizeComponent(size);
        setTransformComponent(transform);
        setAnimationComponent(animation);
        setStateComponent(state);
        setPhysicsComponent(physics);
    }

    public Body createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX() +  getOriginX() , getY() +  getOriginY());
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = factory.getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = Constants.CATEGORY_ITEM;
        fixtureDef.filter.maskBits = Constants.MASK_ITEM;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(6 * WorldUtils.pixelsToMetres);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef).setUserData(coin);
        circleShape.dispose();
        return body;
    }

    public void build() {
        coin.getEntity().add(getTextureComponent());
        coin.getEntity().add(getSizeComponent());
        coin.getEntity().add(getTransformComponent());
        coin.getEntity().add(getAnimationComponent());
        coin.getEntity().add(getStateComponent());
        coin.getEntity().add(getPhysicsComponent());
        factory.getEngine().addEntity(coin.getEntity());
    }

    public Coin getCoin() {
        return coin;
    }
}
