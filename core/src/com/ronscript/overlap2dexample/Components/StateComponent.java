package com.ronscript.overlap2dexample.Components;

import com.badlogic.ashley.core.Component;

/**
 * Created by Ron on 6/26/2016.
 */
public class StateComponent implements Component{
    private int state = 0;
    public float time = 0.0f;

    public void setState(int state){
        this.state = state;
    }

    public int getState(){
        return state;
    }

    public void reset(){
        time = 0.0f;
    }
}
