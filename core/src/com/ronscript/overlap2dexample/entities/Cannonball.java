package com.ronscript.overlap2dexample.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * @author Ron
 * @since 7/8/2016
 */
public class Cannonball extends Bullet {

    public Cannonball() {
    }

    @Override
    public void createBody() {
        texture.region = GameAssets.cannonball;
        size.width = 0.25f;
        size.height = 0.25f;
        transform.origin.set(size.width / 2, size.height / 2);
        Vector2 bp = new Vector2(transform.position.x + transform.origin.x, transform.position.y +  transform.origin.y);
        physics.body = createCircleBody(world, bp, 6);
    }

    public Body createCircleBody(World world, Vector2 position, float radius){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
//        bodyDef.active = false;

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(radius * WorldUtils.pixelsToMetres);

        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.isSensor = true;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_BULLET;
        fixtureDef.filter.maskBits = Constants.MASK_BULLET;
        fixtureDef.shape = circle;
        fixtureDef.density = 0.8f;
        fixtureDef.restitution = 0.2f;
        fixtureDef.friction = 0.99f;

        body.createFixture(fixtureDef).setUserData(this);

        circle.dispose();
        return body;
    }

}
