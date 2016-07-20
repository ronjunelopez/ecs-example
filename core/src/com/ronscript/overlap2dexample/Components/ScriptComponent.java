package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.msg.Telegram;

/**
 * @author Ron
 * @since 7/9/2016
 */
public class ScriptComponent implements Component {

    public final Telegram telegram = new Telegram();
}
