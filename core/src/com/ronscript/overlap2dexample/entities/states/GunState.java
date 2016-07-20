package com.ronscript.overlap2dexample.entities.states;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.builders.PooledBullet;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * @author Ron
 * @since 7/12/2016
 */
public enum GunState implements State<Entity> {

    IDLE() {
        @Override
        public void enter(Entity entity) {

        }
        @Override
        public void update(Entity entity) {
        }
    },
    RELOAD() {
        @Override
        public void enter(Entity entity) {

        }
        @Override
        public void update(Entity entity) {
            GunComponent gun = Mappers.gun.get(entity);
            NodeComponent node = Mappers.node.get(entity);
            AIComponent ai = Mappers.ai.get(entity);
            TransformComponent transform = Mappers.transform.get(entity);
            MovementComponent movement = Mappers.movement.get(entity);
            SizeComponent size = Mappers.size.get(entity);
            float spatialX = transform.position.x;
            float spatialY = transform.position.y;
//            switch(movement.state) {
//                case NORTH:
//                    spatialX += size.width * 0.25f;
//                    spatialY += size.height;
//                    break;
//                case SOUTH:
//                    spatialX += size.width * 0.25f;
//                    spatialY += 0;
//                    break;
//                case WEST:
//                    spatialX += size.width * 0.85f;
//                    spatialY += size.height * 0.25f;
//                    break;
//                case EAST:
//                    spatialX += -size.width * 0.20f;
//                    spatialY += size.height * 0.20f;
//                    break;
//            }

            gun.time += Gdx.graphics.getDeltaTime();
            if (gun.time < 1) {
            } else {
                PooledBullet pooledbullet = gun.cannonballPool.obtain();
                pooledbullet.init(spatialX, spatialY);
                Entity bullet = pooledbullet.entity;
                gun.activeCannonball.add(pooledbullet);
                // @TODO create a clean bullet pool
                node.addChild("bullet", bullet);
                gun.time = 0;
                ai.fsm.changeState(GunState.IDLE);
                Gdx.app.log("", "reload");
            }

        }
    },
    FIRE() {
        @Override
        public void enter(Entity entity) {
            GunComponent gun = Mappers.gun.get(entity);
            NodeComponent node = Mappers.node.get(entity);
            AIComponent ai = Mappers.ai.get(entity);
            TransformComponent transform = Mappers.transform.get(entity);

            if (!node.hasChild("bullet")) {
                ai.fsm.changeState(GunState.RELOAD);
            } else {
                Gdx.app.log("", "fire");
                Entity bulletEntity = node.getChild("bullet");
                BulletComponent bullet = Mappers.bullet.get(bulletEntity);
                if (bullet != null) {
                    bullet.destination.set(gun.sights);
                    bullet.fired = true;
                }
                node.removeChild("bullet");
            }

        }
        @Override
        public void update(Entity entity) {

        }
    };

    public Vector2 getVelocity(float speed, Vector2 currentPosition, Vector2 targetPosition) {
        // Copied sights position and subtracted by bullet position
        Vector2 targetDirection = targetPosition.cpy().sub(currentPosition);
        return targetDirection
                .nor() // normalize to avoid getting the direction as speed
                .scl(speed);  // scaled by speed
    }
    @Override
    public void exit(Entity entity) {

    }

    @Override
    public boolean onMessage(Entity entity, Telegram telegram) {

        return false;
    }
}
