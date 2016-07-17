package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.fsm.StateMachine;

/**
 * @author Ron
 * @since 7/12/2016
 */
public class AIComponent<E, S extends State<E>> implements Component {

    public StateMachine<E, S> stateMachine;
}
