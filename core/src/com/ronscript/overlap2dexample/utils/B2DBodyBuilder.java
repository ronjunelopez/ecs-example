package com.ronscript.overlap2dexample.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Ron
 * @since 7/19/2016
 */
public class B2DBodyBuilder {

    public Body createCharacterBody(World world, float x, float y, Object userdata) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = Constants.MASK_PLAYER;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(12 * WorldUtils.pixelsToMetres);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef).setUserData(userdata);
        circleShape.dispose();
        return body;
    }

}
