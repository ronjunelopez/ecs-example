package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.CharacterComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.PlayerControlledComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.entities.Gun;
import com.ronscript.overlap2dexample.entities.Player;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * @author Ron
 * @since 7/15/2016
 */
public class PlayerBuilder{

    private PooledEngine engine;
    private World world;
    private Player player;

    SizeComponent size;
    CharacterComponent character;
    StateComponent state;
    TextureComponent texture;
    AnimationComponent animation;
    TransformComponent transformation;
    MovementComponent movement;
    PhysicsComponent physics;
    PlayerControlledComponent userControlled;


    public PlayerBuilder(EntityBuilder factory) {
         engine = factory.getEngine();
         world = factory.getWorld();
         player = new Player();
         player.entity = factory.createEntity(Constants.Flags.PLAYER);
         size = engine.createComponent(SizeComponent.class);
         character = engine.createComponent(CharacterComponent.class);
         state = engine.createComponent(StateComponent.class);
         texture = engine.createComponent(TextureComponent.class);
         animation = engine.createComponent(AnimationComponent.class);
         transformation = engine.createComponent(TransformComponent.class);
         movement = engine.createComponent(MovementComponent.class);
         physics = engine.createComponent(PhysicsComponent.class);
         userControlled = engine.createComponent(PlayerControlledComponent.class);
    }

    public void setWeapon(Gun gun) {
        character.gun = gun;
    }

    public void setSize(float width, float height){
        // Pass the character size to transformation size
        size.width = width;
        size.height = height;
    }

    public void setOriginCenter() {
        transformation.origin.set(size.width / 2, size.height / 2);
    }

    public void setTransformation(float posX, float posY) {
        // This position will be used for Texture Region positioning on World Pixel to Meter
        transformation.position.x = posX; // multiplied by PM
        transformation.position.y = posY; // multiplied by PM
    }

    public void setAnimation() {
        texture.region = GameAssets.character_idle;
        animation.map.put(MovementComponent.State.IDLE.ordinal(), GameAssets.character_idle2);
        animation.map.put(MovementComponent.State.SOUTH.ordinal(), GameAssets.character_south);
        animation.map.put(MovementComponent.State.NORTH.ordinal(), GameAssets.character_north);
        animation.map.put(MovementComponent.State.WEST.ordinal(), GameAssets.character_right_side);
        animation.map.put(MovementComponent.State.EAST.ordinal(), GameAssets.character_left_side);
        animation.map.put(MovementComponent.State.NORTH_WEST.ordinal(), GameAssets.character_diag_right_up);
        animation.map.put(MovementComponent.State.NORTH_EAST.ordinal(), GameAssets.character_diag_left_up);
        animation.map.put(MovementComponent.State.SOUTH_WEST.ordinal(), GameAssets.character_diag_right_down);
        animation.map.put(MovementComponent.State.SOUTH_EAST.ordinal(), GameAssets.character_diag_left_down);
        state.setState(MovementComponent.State.IDLE.ordinal());
    }

    public void setBody(Body body) {
        physics.body = body;
    }

    public Body createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(transformation.position.x + transformation.origin.x , transformation.position.y +  transformation.origin.y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.filter.categoryBits = Constants.CATEGORY_PLAYER;
        fixtureDef.filter.maskBits = Constants.MASK_PLAYER;

        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(12 * WorldUtils.pixelsToMetres);
        fixtureDef.shape = circleShape;
        body.createFixture(fixtureDef).setUserData(player);
        circleShape.dispose();
        return body;
    }

    public void build() {

        player.entity.add(size);
        player.entity.add(character);
        player.entity.add(state);
        player.entity.add(texture);
        player.entity.add(animation);
        player.entity.add(transformation);
        player.entity.add(movement);
        player.entity.add(physics);
        player.entity.add(userControlled);


        engine.addEntity(player.entity);
    }

    public Player getPlayer(){
        return player;
    }
}
