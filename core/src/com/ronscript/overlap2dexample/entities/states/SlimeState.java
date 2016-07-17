package com.ronscript.overlap2dexample.entities.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.ronscript.overlap2dexample.entities.Slime;

/**
 * @author Ron
 * @since 7/14/2016
 */
public enum SlimeState implements State<Slime> {
    MOVE_FRONT {
        @Override
        public void enter(Slime slime) {
            slime.state.setState(MOVE_FRONT.ordinal());
        }
    },
    MOVE_RIGHT{
        @Override
        public void enter(Slime slime) {
            slime.state.setState(MOVE_RIGHT.ordinal());
            slime.movement.velocity.set(1, 0);
        }
        @Override
        public void update(Slime slime) {
            if(slime.isSafe()){
                slime.sleep();
            } else {
                slime.physics.body.setLinearVelocity(slime.movement.velocity); // @TODO move to SlimeSystem class
            }
        }

        @Override
        public void exit(Slime slime) {
        }
    }
    ,
    MOVE_LEFT{
        @Override
        public void enter(Slime slime) {
            slime.state.setState(MOVE_LEFT.ordinal());
            slime.movement.velocity.set(-1, 0);
        }
        @Override
        public void update(Slime slime) {
            if(slime.isSafe()){
                slime.sleep();
            } else {
                slime.physics.body.setLinearVelocity(slime.movement.velocity); // @TODO move to SlimeSystem class
            }
        }

        @Override
        public void exit(Slime slime) {
        }
    }
    ,
    MOVE_BACK {

    },
    EXPLODE {
        @Override
        public void enter(Slime slime) {
            slime.state.setState(EXPLODE.ordinal());
            slime.physics.body.setLinearVelocity(0,0);
        }
    },
    RUN_AWAY {
        @Override
        public void update(Slime slime) {
            if (slime.isSafe()) {
                slime.sleep();
            }
            else {
                Gdx.app.log("slime", "moving away");
                slime.moveAwayFromEnemy();
            }
        }
    }
    ,
    SLEEP {
        @Override
        public void enter(Slime slime) {
            slime.state.setState(0);
            slime.physics.body.setLinearVelocity(0,0);
        }

        @Override
        public void update(Slime slime) {
            if(slime.isThreatened()) {
                slime.runAway();
            } else {

            }
        }
    }
    ;

    @Override
    public void enter(Slime entity) {

    }

    @Override
    public void update(Slime entity) {

    }

    @Override
    public void exit(Slime entity) {

    }

    @Override
    public boolean onMessage(Slime entity, Telegram telegram) {
        return false;
    }
}
