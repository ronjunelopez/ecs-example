package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;

/**
 * @author Ron
 * @since 7/17/2016
 */
public class SpriteBuilder implements Spriteable {

    private TextureComponent texture;
    private SizeComponent size;
    private TransformComponent transform;

    public SpriteBuilder() {
    }

    @Override
    public void setTextureComponent(TextureComponent textureComponent) {
        this.texture = textureComponent;
    }

    @Override
    public void setSizeComponent(SizeComponent sizeComponent) {
        this.size = sizeComponent;
    }

    @Override
    public void setTransformComponent(TransformComponent transformComponent) {
        this.transform = transformComponent;
    }

    @Override
    public TextureComponent getTextureComponent() {
        return texture;
    }

    @Override
    public SizeComponent getSizeComponent() {
        return size;
    }

    @Override
    public TransformComponent getTransformComponent() {
        return transform;
    }

    @Override
    public void setTexture(TextureRegion textureRegion) {
        this.texture.region = textureRegion;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture.region.setTexture(texture);
    }

    @Override
    public void setSize(float width, float height) {
        size.width = width;
        size.height = height;
    }

    @Override
    public void setTransform(float posX, float posY, float angle) {
        setPosition(posX,posY);
        setRotation(angle * MathUtils.radiansToDegrees);
    }
    @Override
    public void setPosition(float posX, float posY) {
        transform.position.set(posX, posY);
    }

    @Override
    public void setBounds(float posX, float posY, float width, float height) {
        setPosition(posX, posY);
        setSize(width, height);
    }
    @Override
    public void setOriginCenter() {
        setOrigin(size.width / 2, size.height / 2);
    }

    @Override
    public TextureRegion getTextureRegion() {
        return texture.region;
    }

    @Override
    public Texture getTexture() {
        return texture.region.getTexture();
    }

    @Override
    public float getWidth() {
        return size.width;
    }

    @Override
    public float getHeight() {
        return size.height;
    }

    @Override
    public Vector2 getPosition() {
        return transform.position;
    }

    @Override
    public Vector2 getOrigin() {
        return transform.origin;
    }

    @Override
    public Vector2 getScale() {
        return transform.scale;
    }

    @Override
    public void setRotation(float rotation) {
        transform.rotation = rotation;
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        transform.scale.set(scaleX, scaleY);
    }

    @Override
    public void setOrigin(float originX, float originY) {
        transform.origin.set(originX, originY);
    }

    @Override
    public float getScaleX() {
        return getScale().x;
    }

    @Override
    public float getScaleY() {
        return getScale().y;
    }

    @Override
    public float getOriginX() {
        return getOrigin().x;
    }

    @Override
    public float getOriginY() {
        return getOrigin().y;
    }

    @Override
    public float getX() {
        return getPosition().x;
    }

    @Override
    public float getY() {
        return getPosition().y;
    }
}
