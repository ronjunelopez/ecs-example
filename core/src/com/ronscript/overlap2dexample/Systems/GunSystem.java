package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.entities.builders.EntityBuilder;
import com.ronscript.overlap2dexample.entities.builders.PooledBullet;
import com.ronscript.overlap2dexample.entities.states.GunState;
import com.ronscript.overlap2dexample.utils.Mappers;

/**
 * Created by Ron on 7/5/2016.
 */
public class GunSystem extends IteratingSystem implements Disposable{

    EntityBuilder builder;

    public GunSystem(EntityBuilder builder) {
        super(Mappers.gunFamily);
        this.builder = builder;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mappers.transform.get(entity);
        SizeComponent size = Mappers.size.get(entity);
        GunComponent gun = Mappers.gun.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);
        TextureComponent texture = Mappers.texture.get(entity);
        NodeComponent node = Mappers.node.get(entity);


//       entity.remove(AIComponent.class);

        AIComponent ai = Mappers.ai.get(entity);


        if (ai.fsm.isInState(GunState.RELOAD)) {


        }

        if (ai.fsm.isInState(GunState.FIRE)) {

        }


        PooledBullet item;
        int len = gun.activeCannonball.size;
        for (int i = len; --i >= 0;) {
            item = gun.activeCannonball.get(i);
            BulletComponent b = Mappers.bullet.get(item.entity);
            if (b.alive == false) {
                Gdx.app.log("", "free");
                gun.activeCannonball.removeIndex(i);
                gun.cannonballPool.free(item);
            }
        }


        updateTexture(entity);
    }


    private void updateTexture(Entity entity) {
        MovementComponent movement = Mappers.movement.get(entity);
        TextureComponent texture = Mappers.texture.get(entity);
        SizeComponent size = Mappers.size.get(entity);

//        switch (movement.state) {
//            case SOUTH:
//                size.width = 12 * WorldUtils.pixelsToMetres;
//                size.height = 14 * WorldUtils.pixelsToMetres;
//                texture.region.setTexture(GameAssets.cannon_down);
//                break;
//            case NORTH:
//                size.width = 12 * WorldUtils.pixelsToMetres;
//                size.height = 14 * WorldUtils.pixelsToMetres;
//                texture.region.setTexture(GameAssets.cannon_up);
//                break;
//            case WEST:
//                size.width = 17 * WorldUtils.pixelsToMetres;
//                size.height = 10 * WorldUtils.pixelsToMetres;
//                TextureRegion cannon_right = new TextureRegion(GameAssets.cannon_side);
//                if(cannon_right.isFlipX()) {
//                    cannon_right.flip(true, false);
//                }
//                texture.region = cannon_right;
//                break;
//            case EAST:
//                size.width = 17 * WorldUtils.pixelsToMetres;
//                size.height = 10 * WorldUtils.pixelsToMetres;
//                TextureRegion cannon_left = new TextureRegion(GameAssets.cannon_side);
//                cannon_left.flip(true, false);
//                texture.region = cannon_left;
//                break;
//            case NORTH_WEST:
//                size.width = 15 * WorldUtils.pixelsToMetres;
//                size.height = 13 * WorldUtils.pixelsToMetres;
//                TextureRegion cannon_diagup = new TextureRegion(GameAssets.cannon_diagup);
//                if(cannon_diagup.isFlipX()) {
//                    cannon_diagup.flip(true, false);
//                }
//                texture.region = cannon_diagup;
//                break;
//            case NORTH_EAST:
//                size.width = 15 * WorldUtils.pixelsToMetres;
//                size.height = 13 * WorldUtils.pixelsToMetres;
//                TextureRegion cannon_diagupleft = new TextureRegion(GameAssets.cannon_diagup);
//
//                cannon_diagupleft.flip(true, false);
//
//                texture.region = cannon_diagupleft;
//                break;
//            case SOUTH_WEST:
//                size.width = 16 * WorldUtils.pixelsToMetres;
//                size.height = 13 * WorldUtils.pixelsToMetres;
//                TextureRegion cannon_diagdown = new TextureRegion(GameAssets.cannon_diagdown);
//                if(cannon_diagdown.isFlipX()) {
//                    cannon_diagdown.flip(true, false);
//                }
//                texture.region = cannon_diagdown;
//                break;
//            case SOUTH_EAST:
//                size.width = 16 * WorldUtils.pixelsToMetres;
//                size.height = 13 * WorldUtils.pixelsToMetres;
//                TextureRegion cannon_diagdownleft = new TextureRegion(GameAssets.cannon_diagdown);
//
//                cannon_diagdownleft.flip(true, false);
//
//                texture.region = cannon_diagdownleft;
//                break;
//        }

    }

    @Override
    public void dispose() {

    }
}
