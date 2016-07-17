package com.ronscript.overlap2dexample.entities.states;

import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.ronscript.overlap2dexample.entities.Gun;

/**
 * @author Ron
 * @since 7/12/2016
 */
public enum GunState implements State<Gun> {

    IDLE() {
        @Override
        public void enter(Gun entity) {

        }
        @Override
        public void update(Gun entity) {
        }
    },
    RELOAD() {
        @Override
        public void enter(Gun entity) {

        }
        @Override
        public void update(Gun entity) {
            entity.reload();
        }
    },
    FIRE() {
        @Override
        public void enter(Gun entity) {

        }
        @Override
        public void update(Gun entity) {
            entity.fire();
        }
    }
    ;

    @Override
    public void exit(Gun entity) {

    }

    @Override
    public boolean onMessage(Gun entity, Telegram telegram) {
        return false;
    }
}
