package com.starun.www.starun.model;


/**
 * Created by hjq on 2016/3/28.
 */
public class Exercise {
    public enum State{
        START,
        PAUSE,
        STOP
    }

    protected State state;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
