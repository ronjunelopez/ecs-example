package com.ronscript.overlap2dexample.Systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;
import com.ronscript.overlap2dexample.Managers.CameraManager;
import com.ronscript.overlap2dexample.Views.Stages.Hud;
import com.ronscript.overlap2dexample.utils.Mappers;

import java.util.Comparator;

/**
 * Created by Ron on 6/13/2016.
 */
public class RenderingSystem extends SortedIteratingSystem {


    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Hud hud;

    private boolean debug = false;

    private TiledMapRenderer tiledMapRenderer;

    public RenderingSystem(SpriteBatch batch, TiledMapRenderer tiledMapRenderer) {

        super(Family.all(SizeComponent.class, TextureComponent.class, TransformComponent.class).get(), new ZComparator());

        this.batch = batch;
        this.tiledMapRenderer = tiledMapRenderer;

        CameraManager.getInstance().setupCamera();
        camera = CameraManager.getInstance().getCamera();

        hud = new Hud(batch);
        hud.getStage().setDebugAll(debug);

    }

    public SpriteBatch getSpriteBath(){return batch;}
    public OrthographicCamera getCamera(){
        return camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent texture = Mappers.texture.get(entity);
        SizeComponent size = Mappers.size.get(entity);
        TransformComponent transform = Mappers.transform.get(entity);

        if(texture != null && size !=null && transform !=null){
            if (texture.region.getTexture() == null) {
                return;
            }
            batch.draw(texture.region,
                    transform.position.x - transform.origin.x,
                    transform.position.y - transform.origin.y,
                    transform.origin.x,
                    transform.origin.y,
                    size.width,
                    size.height,
                    transform.scale.x,
                    transform.scale.y,
                    transform.rotation
                    );
        }

    }

    @Override
    public void update (float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        tiledMapRenderer.setView(camera); // draw only what camera's size
        tiledMapRenderer.render();
        batch.begin();
        super.update(deltaTime);
        batch.end();
        hud.update(deltaTime);
    }

    private static class ZComparator implements Comparator<Entity> {
        @Override
        public int compare(Entity e1, Entity e2) {
            return (int)Math.signum(Mappers.texture.get(e1).z - Mappers.texture.get(e2).z);
        }
    }
}
