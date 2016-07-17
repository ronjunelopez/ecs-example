package com.ronscript.overlap2dexample.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Family;
import com.ronscript.overlap2dexample.Components.AIComponent;
import com.ronscript.overlap2dexample.Components.AnimationComponent;
import com.ronscript.overlap2dexample.Components.BulletComponent;
import com.ronscript.overlap2dexample.Components.CameraComponent;
import com.ronscript.overlap2dexample.Components.GunComponent;
import com.ronscript.overlap2dexample.Components.MovementComponent;
import com.ronscript.overlap2dexample.Components.NodeComponent;
import com.ronscript.overlap2dexample.Components.PhysicsComponent;
import com.ronscript.overlap2dexample.Components.CharacterComponent;
import com.ronscript.overlap2dexample.Components.SizeComponent;
import com.ronscript.overlap2dexample.Components.StateComponent;
import com.ronscript.overlap2dexample.Components.TextureComponent;
import com.ronscript.overlap2dexample.Components.TransformComponent;

/**
 * Created by Ron on 6/27/2016.
 */
public class Mappers {

    public static ComponentMapper<AnimationComponent> animation = ComponentMapper.getFor(AnimationComponent.class);
    public static ComponentMapper<CameraComponent> camera = ComponentMapper.getFor(CameraComponent.class);
    public static ComponentMapper<CharacterComponent> character = ComponentMapper.getFor(CharacterComponent.class);
    public static ComponentMapper<MovementComponent> movement = ComponentMapper.getFor(MovementComponent.class);
    public static ComponentMapper<PhysicsComponent> physics = ComponentMapper.getFor(PhysicsComponent.class);
    public static ComponentMapper<StateComponent> state = ComponentMapper.getFor(StateComponent.class);
    public static ComponentMapper<TextureComponent> texture = ComponentMapper.getFor(TextureComponent.class);
    public static ComponentMapper<TransformComponent> transform = ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<SizeComponent> size = ComponentMapper.getFor(SizeComponent.class);
    public static ComponentMapper<BulletComponent> bullet = ComponentMapper.getFor(BulletComponent.class);
    public static ComponentMapper<GunComponent> gun = ComponentMapper.getFor(GunComponent.class);
    public static ComponentMapper<AIComponent> stateMachine = ComponentMapper.getFor(AIComponent.class);
    public static ComponentMapper<NodeComponent> node = ComponentMapper.getFor(NodeComponent.class);

    public static Family gunFamily = Family.all(GunComponent.class, TransformComponent.class, SizeComponent.class).get();
    public static Family animationFamily = Family.all(AnimationComponent.class, TextureComponent.class,  StateComponent.class).get();
    public static Family bulletFamily = Family.all(BulletComponent.class, TransformComponent.class, SizeComponent.class, PhysicsComponent.class, MovementComponent.class).get();
}
