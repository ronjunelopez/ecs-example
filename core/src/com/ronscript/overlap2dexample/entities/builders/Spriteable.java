package com.ronscript.overlap2dexample.entities.builders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;

/**
 * @author Ron
 * @since 7/16/2016
 */
public interface Spriteable {
    TextureComponent getTextureComponent();
    SizeComponent getSizeComponent();
    TransformComponent getTransformComponent();
    TextureRegion getTextureRegion();
    Texture getTexture();
    float getWidth();
    float getHeight();
    Vector2 getPosition();
    Vector2 getOrigin();
    Vector2 getScale();
    float getScaleX();
    float getScaleY();
    float getOriginX();
    float getOriginY();
    float getX();
    float getY();
    void setTextureComponent(TextureComponent textureComponent);
    void setSizeComponent(SizeComponent sizeComponent);
    void setTransformComponent(TransformComponent transformComponent);
    void setTexture(TextureRegion textureRegion);
    void setTexture(Texture texture);
    void setSize(float width, float height);
    void setTransform(float posX, float posY, float angle);
    void setRotation(float rotation);
    void setPosition(float posX, float posY);
    void setBounds(float posX, float posY, float width, float height);
    void setScale(float scaleX, float scaleY);
    void setOrigin(float originX, float originY);
    void setOriginCenter();
}
