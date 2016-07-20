package com.ronscript.overlap2dexample.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TargetComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.entities.states.SlimeState;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * @author Ron
 * @since 7/14/2016
 */
public class Slime{

    public Entity entity;

    public TextureComponent graphic;
    public SizeComponent size;
    public TransformComponent transform;
    public AnimationComponent animation;
    public StateComponent state;
    public MovementComponent movement;
    public PhysicsComponent physics;
    public AIComponent ai;
    public TargetComponent target;

    private boolean safe = true;

    public Slime(EntityBuilder factory, float x, float y) {
        entity = factory.createEntity(Constants.Flags.SLIME);

        graphic = factory.getEngine().createComponent(TextureComponent.class);
        size = factory.getEngine().createComponent(SizeComponent.class);
        transform = factory.getEngine().createComponent(TransformComponent.class);
        animation = factory.getEngine().createComponent(AnimationComponent.class);
        state = factory.getEngine().createComponent(StateComponent.class);
        movement = factory.getEngine().createComponent(MovementComponent.class);
        physics = factory.getEngine().createComponent(PhysicsComponent.class);
        ai = factory.getEngine().createComponent(AIComponent.class);
        target = factory.getEngine().createComponent(TargetComponent.class);

        transform.position.x = x;
        transform.position.y = y;
        size.width = 0.5f;
        size.height = 0.5f;
        transform.origin.set(size.width / 2, size.height / 2);
        // physics body creation
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x  + transform.origin.x, y + transform.origin.y);
        bodyDef.fixedRotation = true;
        physics.body = factory.getWorld().createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = Constants.CATEGORY_ENEMY;
        fixtureDef.filter.maskBits = Constants.MASK_ENEMY;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 1;
        fixtureDef.density = 100;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(7 * 0.03125f);
        fixtureDef.shape = circleShape;
        physics.body.createFixture(fixtureDef).setUserData(this);
        circleShape.dispose();

        CircleShape range = new CircleShape();
        range.setRadius(40 * 0.03125f);
        fixtureDef.shape = range;
        fixtureDef.filter.categoryBits = Constants.CATEGORY_SENSOR;
        fixtureDef.filter.maskBits = Constants.MASK_SENSOR;
        fixtureDef.isSensor = true;
        physics.body.createFixture(fixtureDef).setUserData(this);
        range.dispose();

        animation.isLooping = true;
        animation.map.put(SlimeState.MOVE_FRONT.ordinal(), GameAssets.slime_front);
        animation.map.put(SlimeState.MOVE_BACK.ordinal(), GameAssets.slime_back);
        animation.map.put(SlimeState.MOVE_LEFT.ordinal(), GameAssets.slime_left_side);
        animation.map.put(SlimeState.MOVE_RIGHT.ordinal(), GameAssets.slime_right_side);
        animation.map.put(SlimeState.EXPLODE.ordinal(), GameAssets.slime_explode);

        state.setState(0);
        ai.fsm = new DefaultStateMachine(this, SlimeState.SLEEP);

        entity.add(graphic);
        entity.add(size);
        entity.add(animation);
        entity.add(transform);
        entity.add(state);
        entity.add(physics);
        entity.add(ai);
        entity.add(target);

        factory.getEngine().addEntity(entity);
    }

    public void sense(Entity player) {
        target.player = player;
        safe = false;
    }

    public void setSafe(boolean safe){
        this.safe = safe;
    }

    public boolean isSafe() {
        return safe;
    }


    public void moveAwayFromEnemy(){
        Entity player = target.player;
        PhysicsComponent playerPhysics =  Mappers.physics.get(player);
        Body playerBody  = playerPhysics.body;
        playerBody.getPosition();
        MovementComponent playerMovement = Mappers.movement.get(player);

        switch (playerMovement.state) {
            case WEST:
                ai.fsm.changeState(SlimeState.MOVE_RIGHT);
                break;
            case EAST:
                ai.fsm.changeState(SlimeState.MOVE_LEFT);
                break;
        }
    }

    public boolean isThreatened() {
        return !safe ? true: false;
    }

    public void sleep() {
        ai.fsm.changeState(SlimeState.SLEEP);
    }

    public void runAway() {
        ai.fsm.changeState(SlimeState.RUN_AWAY);
    }

    public void kill() {
        ai.fsm.changeState(SlimeState.EXPLODE);
    }

}
