package com.ronscript.overlap2dexample.Managers;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.ronscript.overlap2dexample.BaseGame;
import com.ronscript.overlap2dexample.BaseScreen;
import com.ronscript.overlap2dexample.Views.Screens.MainMenu;
import com.ronscript.overlap2dexample.Views.Screens.Play;
import com.ronscript.overlap2dexample.Views.Screens.Splash;

/**
 * Created by Ron on 6/4/2016.
 */
public class ScreenManager implements Disposable{

    public enum EnumScreen {SPLASH,MAINMENU,PLAY,HELP,CREDITS}
    private Screen currentScreen;
    private Screen previousScreen;
    private EnumScreen enumScreen;

    private Array<Screen> screens;

    private Splash splash;
    private MainMenu mainmenu;
    private Play play;

    private final BaseGame game;

    public  ScreenManager(BaseGame game){
        this.game = game;

        splash = new Splash(game);
        mainmenu = new MainMenu(game);
        play = new Play(game);

        screens = new Array<Screen>();

        screens.add(splash);
        screens.add(mainmenu);
        screens.add(play);
    }

    public BaseGame getGame(){
        return game;
    }

    public void setScreen(EnumScreen enumScreen){
        this.enumScreen = enumScreen;
        BaseScreen s = getCurrentScreen();
        previousScreen = s;
        this.game.setScreen(s);
    }

    public void previousScreen(){
        if(previousScreen != null) {
            this.game.setScreen(previousScreen);
        }
    }

    public BaseScreen getCurrentScreen(){
        switch (enumScreen){
            case SPLASH:
                currentScreen = screens.contains(splash,true) ? splash : null;
                break;
            case MAINMENU:
                currentScreen = screens.contains(mainmenu,true) ? mainmenu : null;
                break;
            case PLAY:
                currentScreen = screens.contains(play,true) ? play : null;
                break;
        }
        return (BaseScreen) currentScreen;
    }


    @Override
    public void dispose() {
        currentScreen = null;
        previousScreen = null;
        for (Screen s: screens){
            s.dispose();
        }
        screens.clear();
        screens = null;
    }
}
