package com.ronscript.overlap2dexample.Listeners;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.entities.Slime;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.utils.Constants;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 6/30/2016.
 */
public class WorldContactListener implements ContactListener {

    EntityBuilder factory;

    public WorldContactListener(EntityBuilder factory) {
        this.factory = factory;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int categoryBits = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch(categoryBits){
            case Constants.CATEGORY_SENSOR | Constants.CATEGORY_PLAYER:
                onSensorAndPlayerCollision(fixtureA, fixtureB);
                break;
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_ENEMY:
                onPlayerAndEnemyCollision(fixtureA, fixtureB);
                break;
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_ITEM:
                onPlayerAndItemCollision(fixtureA, fixtureB);
                break;
            case Constants.CATEGORY_BULLET | Constants.CATEGORY_ENEMY:
//                onBulletAndEnemyCollision(fixtureA,fixtureB);
                break;
            case Constants.CATEGORY_BULLET | Constants.CATEGORY_GROUND:
//                onBulletAndEnemyCollision(fixtureA,fixtureB);
                if (fixtureA.getUserData() instanceof Entity) {
                    Entity bullet = (Entity) fixtureA.getUserData();
                    BulletComponent component = Mappers.bullet.get(bullet);
                    component.alive = false;
//                      factory.removeEntity(bullet);
                } else {
                    Entity bullet = (Entity) fixtureB.getUserData();
                    BulletComponent component = Mappers.bullet.get(bullet);
                    component.alive = false;
//                      factory.removeEntity(bullet);
                }
                break;
            case Constants.CATEGORY_PLAYER | Constants.CATEGORY_PLAYER:
                Gdx.app.log("Player", "Hits character");
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        int categoryBits = fixtureA.getFilterData().categoryBits | fixtureB.getFilterData().categoryBits;

        switch (categoryBits) {
            case Constants.CATEGORY_SENSOR | Constants.CATEGORY_PLAYER:
                onSensorAndPlayerEndContact(fixtureA, fixtureB);
                break;
        }

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean isSlime(Fixture fixture) {
        return fixture.getUserData() instanceof Slime ? true : false;
    }

    public void onSensorAndPlayerEndContact(Fixture fixtureA, Fixture fixtureB) {
        if(isSlime(fixtureB)){
            Gdx.app.log("Fixture B end", "SLIME");
            Slime slime = (Slime) fixtureB.getUserData();
            slime.setSafe(true);
        } else {
            Gdx.app.log("Fixture A end", "SLIME");
            Slime slime = (Slime) fixtureA.getUserData();
            slime.setSafe(true);
        }
    }

    public void onSensorAndPlayerCollision(Fixture fixtureA, Fixture fixtureB) {
        if(fixtureB.getUserData() instanceof  Slime){
            Gdx.app.log("Fixture B", "SLIME");
            Slime slime = (Slime) fixtureB.getUserData();
            Entity player = (Entity) fixtureA.getUserData();
            slime.sense(player);
        } else {
            Gdx.app.log("Fixture A", "SLIME");
            Slime slime = (Slime) fixtureA.getUserData();


            Entity player = (Entity) fixtureB.getUserData();
            slime.sense(player);
        }
    }

    public void onBulletAndEnemyCollision(Fixture fixtureA, Fixture fixtureB) {
        Gdx.app.log("Bullet", "Hits Enemy");
        if (fixtureA.getUserData() instanceof Entity) {
            Gdx.app.log("Entity A", "BULLET");
            Entity bullet = (Entity) fixtureA.getUserData();
//            Slime slime = (Slime) fixtureB.getUserData();


            factory.removeEntity(bullet);
        } else {
            Gdx.app.log("Entity B", "BULLET");
            Entity bullet = (Entity) fixtureB.getUserData();
            factory.removeEntity(bullet);
        }

        if(isSlime(fixtureB)){
            Gdx.app.log("Entity B", "SLIME");
            Slime slime = (Slime) fixtureB.getUserData();
            slime.kill();
        }
        if (isSlime(fixtureA)) {
            Gdx.app.log("Entity A", "SLIME");
            Slime slime = (Slime) fixtureA.getUserData();
            slime.kill();
        }
    }

    public void onPlayerAndItemCollision(Fixture fixtureA, Fixture fixtureB) {

    }

    public void onPlayerAndEnemyCollision(Fixture fixtureA, Fixture fixtureB) {
        Gdx.app.log("Player", "Hits enemy");
        if(fixtureA.getUserData() instanceof  Entity){
            Gdx.app.log("Entity A", "Player");
        } else {
            Gdx.app.log("Entity B", "Player");
        }

        if(fixtureB.getUserData() instanceof  Slime){
            Gdx.app.log("Entity B", "SLIME");
            Slime slime = (Slime) fixtureB.getUserData();

            Entity player = (Entity) fixtureA.getUserData();
            slime.sense(player);
        } else {
            Gdx.app.log("Entity A", "SLIME");
            Slime slime = (Slime) fixtureA.getUserData();


            Entity player = (Entity) fixtureB.getUserData();
            slime.sense(player);
        }
    }
}
