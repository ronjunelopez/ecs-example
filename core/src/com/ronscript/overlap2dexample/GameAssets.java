package com.ronscript.overlap2dexample;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.ronscript.overlap2dexample.Views.Screens.MainMenu;
import com.ronscript.overlap2dexample.Views.Screens.Play;

/**
 * Created by Ron on 6/13/2016.
 */
public class GameAssets {
    public static TextureAtlas atlas;

    /*
    *  Character Variables
    * */
    public static TextureRegion character_idle;
    public static Animation character_idle2;
    public static Animation character_south;
    public static Animation character_north;
    // diagonal
    public static Animation character_diag_right_down;
    public static Animation character_diag_left_down;
    public static Animation character_diag_right_up;
    public static Animation character_diag_left_up;
    //    public static Animation character_side;
    public static Animation character_left_side;
    public static Animation character_right_side;
    /*
    Items Variables
     */
    public static Animation coin;
    public static Texture cannon_down;
    public static Texture cannon_up;
    public static Texture cannon_side;
    public static Texture cannon_diagup;
    public static Texture cannon_diagdown;
    public static TextureRegion cannonball;

    /*
    Enemy Variables
     */
    public static Animation slime_front;
    public static Animation slime_back;
    public static Animation slime_left_side;
    public static Animation slime_right_side;
    public static Animation slime_explode;

    public static Skin loadingSkin;



    public static void loadLoadingSkin(){
        loadingSkin = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        loadingSkin.add("bg", new Texture(pixmap));
        loadingSkin.add("barBg", new Texture("data/barBg.png"));
        loadingSkin.add("bar",  new Texture("data/bar.png"));
        loadingSkin.add("knob",  new Texture("data/barKnob.png"));
    }

    public static void load(AssetManager manager){

//        manager.load("ron_gfx/ron_movement.pack", TextureAtlas.class);
//        manager.load("barBg.png", Texture.class );
//        manager.load("duterte_idle.png", Texture.class);

        // Characters
        manager.load("characters/1.png", Texture.class);
        manager.load("characters/1_diagdown.png", Texture.class);
        manager.load("characters/1_diagup.png", Texture.class);
        manager.load("characters/1_north.png", Texture.class);
        manager.load("characters/1_side.png", Texture.class);
        manager.load("characters/1_south.png", Texture.class);
        manager.load("characters/1_south2.png", Texture.class);
        // Items
        manager.load("items/coin2.png", Texture.class);
        manager.load("items/cannonball.png", Texture.class);
        manager.load("items/cannon_down.png", Texture.class);
        manager.load("items/cannon_up.png", Texture.class);
        manager.load("items/cannon_side.png", Texture.class);
        manager.load("items/cannon_diagup.png", Texture.class);
        manager.load("items/cannon_diagdown.png", Texture.class);

        // Enemies
        manager.load("enemies/slime1_front.png", Texture.class);
        manager.load("enemies/slime1_back.png", Texture.class);
        manager.load("enemies/slime1_side.png", Texture.class);
        manager.load("enemies/slime_explode.png", Texture.class);
    }

    public static void create(BaseScreen screen, AssetManager manager){


        if (screen instanceof MainMenu){

        }
        if (screen instanceof Play){
            createCharacters(manager);
            createItems(manager);
            createEnemies(manager);
        }
    }


    private static void createCharacters(AssetManager manager){
        Array<TextureRegion> frames = new Array<TextureRegion>();


        character_idle = new TextureRegion(manager.get("characters/1.png", Texture.class),0,0,16,21);

        frames.add(new TextureRegion(manager.get("characters/1.png",Texture.class), 0,0, 16,21));
        character_idle2 = new Animation(0.2f, frames);
        frames.clear();

        frames.add(new TextureRegion(manager.get("characters/1_north.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_north.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_north.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_north.png",Texture.class), 60,0, 20,24));
        character_north = new Animation(0.2f, frames);
        frames.clear();


        frames.add(new TextureRegion(manager.get("characters/1_south.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_south.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_south.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_south.png",Texture.class), 60,0, 20,24));
        character_south = new Animation(0.2f, frames);
        frames.clear();


        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 60,0, 20,24));
        character_right_side = new Animation(0.2f, frames);
        frames.clear();

        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_side.png",Texture.class), 60,0, 20,24));
        character_left_side = new Animation(0.2f, frames);
        for(TextureRegion tr: frames){
            tr.flip(true, false);
        }
        frames.clear();

        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 60,0, 20,24));
        character_diag_right_up = new Animation(0.2f, frames);
        frames.clear();

        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagup.png",Texture.class), 60,0, 20,24));
        character_diag_left_up = new Animation(0.2f, frames);
        for(TextureRegion tr: frames){
            tr.flip(true, false);
        }
        frames.clear();

        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 60,0, 20,24));
        character_diag_right_down = new Animation(0.2f, frames);
        frames.clear();

        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 0,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 20,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 40,0, 20,24));
        frames.add(new TextureRegion(manager.get("characters/1_diagdown.png",Texture.class), 60,0, 20,24));
        character_diag_left_down = new Animation(0.2f, frames);
        for(TextureRegion tr: frames){
            tr.flip(true, false);
        }
        frames.clear();
    }
    private static void createItems(AssetManager manager){
        cannonball = new TextureRegion(manager.get("items/cannonball.png", Texture.class),0,0, 17,17);


        Array<TextureRegion> frames = new Array<TextureRegion>();
        int frameCount = 8;
        for (int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(manager.get("items/coin2.png", Texture.class), i*16,0,16,16));
        }
        coin = new Animation(0.1f, frames);
        frames.clear();

        cannon_down = manager.get("items/cannon_down.png", Texture.class);
        cannon_up = manager.get("items/cannon_up.png", Texture.class);
        cannon_side = manager.get("items/cannon_side.png", Texture.class);
        cannon_diagup = manager.get("items/cannon_diagup.png", Texture.class);
        cannon_diagdown = manager.get("items/cannon_diagdown.png", Texture.class);

        coin.setPlayMode(Animation.PlayMode.LOOP);

    }
    private static void createEnemies(AssetManager manager){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        int frameCount = 4;
        for (int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(manager.get("enemies/slime1_front.png", Texture.class), i*16,0,16,16));
        }
        slime_front = new Animation(0.1f, frames);
        slime_front.setPlayMode(Animation.PlayMode.LOOP);
        frames.clear();


        for (int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(manager.get("enemies/slime1_back.png", Texture.class), i*16,0,16,16));
        }
        slime_back = new Animation(0.1f, frames);
        slime_back.setPlayMode(Animation.PlayMode.LOOP);
        frames.clear();


        for (int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(manager.get("enemies/slime1_side.png", Texture.class), i*16,0,16,16));
        }

        slime_left_side = new Animation(0.1f, frames);
        slime_left_side.setPlayMode(Animation.PlayMode.LOOP);

        frames.clear();

        for (int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(manager.get("enemies/slime1_side.png", Texture.class), i*16,0,16,16));
        }
        for (TextureRegion tr: frames) {
            tr.flip(true, false);
        }
        slime_right_side = new Animation(0.1f, frames);
        slime_right_side.setPlayMode(Animation.PlayMode.LOOP);

        frames.clear();

        for (int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(manager.get("enemies/slime_explode.png", Texture.class), i*37,0,37,41));
        }
        slime_explode = new Animation(0.2f, frames);
        frames.clear();
    }
}
