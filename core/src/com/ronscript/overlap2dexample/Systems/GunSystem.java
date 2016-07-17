package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.GameAssets;
import com.ronscript.overlap2dexample.entities.Cannonball;
import com.ronscript.overlap2dexample.utils.Mappers;
import com.ronscript.overlap2dexample.utils.WorldUtils;

/**
 * Created by Ron on 7/5/2016.
 */
public class GunSystem extends IteratingSystem implements Disposable{

    public GunSystem() {
        super(Mappers.gunFamily);
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mappers.transform.get(entity);
        SizeComponent size = Mappers.size.get(entity);
        GunComponent gun = Mappers.gun.get(entity);
        MovementComponent movement = Mappers.movement.get(entity);
        TextureComponent renderable = Mappers.texture.get(entity);

        Cannonball item;
        int len = gun.activeCannonball.size;
        for (int i = len; --i >= 0;) {
            item = gun.activeCannonball.get(i);
            if (item.bullet.alive == false) {
                Gdx.app.log("", "free");
                gun.activeCannonball.removeIndex(i);
                gun.cannonballPool.free(item);
            }
        }

        AIComponent ai = Mappers.stateMachine.get(entity);
//        ai.stateMachine.isInState(GunState.RELOAD)

//        SpriteComponent sprite = Mappers.sprite.get(entity);
//        sprite.sprite.setPosition(transform.position.x, transform.position.y);
        updateTexture(movement, renderable, size);
    }

    private void updateTexture(MovementComponent movement, TextureComponent renderable, SizeComponent size){
        switch (movement.state) {
            case SOUTH:
                size.width = 12 * WorldUtils.pixelsToMetres;
                size.height = 14 * WorldUtils.pixelsToMetres;
                renderable.region.setTexture(GameAssets.cannon_down);
                break;
            case NORTH:
                size.width = 12 * WorldUtils.pixelsToMetres;
                size.height = 14 * WorldUtils.pixelsToMetres;
                renderable.region.setTexture(GameAssets.cannon_up);
                break;
            case WEST:
                size.width = 17 * WorldUtils.pixelsToMetres;
                size.height = 10 * WorldUtils.pixelsToMetres;
                TextureRegion cannon_right = new TextureRegion(GameAssets.cannon_side);
                if(cannon_right.isFlipX()) {
                    cannon_right.flip(true, false);
                }
                renderable.region = cannon_right;
                break;
            case EAST:
                size.width = 17 * WorldUtils.pixelsToMetres;
                size.height = 10 * WorldUtils.pixelsToMetres;
                TextureRegion cannon_left = new TextureRegion(GameAssets.cannon_side);
                cannon_left.flip(true, false);
                renderable.region = cannon_left;
                break;
            case NORTH_WEST:
                size.width = 15 * WorldUtils.pixelsToMetres;
                size.height = 13 * WorldUtils.pixelsToMetres;
                TextureRegion cannon_diagup = new TextureRegion(GameAssets.cannon_diagup);
                if(cannon_diagup.isFlipX()) {
                    cannon_diagup.flip(true, false);
                }
                renderable.region = cannon_diagup;
                break;
            case NORTH_EAST:
                size.width = 15 * WorldUtils.pixelsToMetres;
                size.height = 13 * WorldUtils.pixelsToMetres;
                TextureRegion cannon_diagupleft = new TextureRegion(GameAssets.cannon_diagup);

                cannon_diagupleft.flip(true, false);

                renderable.region = cannon_diagupleft;
                break;
            case SOUTH_WEST:
                size.width = 16 * WorldUtils.pixelsToMetres;
                size.height = 13 * WorldUtils.pixelsToMetres;
                TextureRegion cannon_diagdown = new TextureRegion(GameAssets.cannon_diagdown);
                if(cannon_diagdown.isFlipX()) {
                    cannon_diagdown.flip(true, false);
                }
                renderable.region = cannon_diagdown;
                break;
            case SOUTH_EAST:
                size.width = 16 * WorldUtils.pixelsToMetres;
                size.height = 13 * WorldUtils.pixelsToMetres;
                TextureRegion cannon_diagdownleft = new TextureRegion(GameAssets.cannon_diagdown);

                cannon_diagdownleft.flip(true, false);

                renderable.region = cannon_diagdownleft;
                break;
        }

    }

    @Override
    public void dispose() {

    }
}
