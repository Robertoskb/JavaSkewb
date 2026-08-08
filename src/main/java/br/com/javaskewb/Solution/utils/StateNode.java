package br.com.javaskewb.Solution.utils;

import br.com.javaskewb.Cube.State;

import java.util.ArrayList;

public class StateNode {
    private State state;
    private int distance;
    private Scramble scramble = new Scramble();

    public StateNode(State state, int distance){
        setState(state);
        setDistance(distance);
    }

    public StateNode(State state, int distance, Scramble scramble){
        setState(state);
        setDistance(distance);
        setScramble(scramble);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Scramble getScramble() {
        return scramble;
    }

    public void setScramble(Scramble scramble) {
        this.scramble = scramble;
    }

}
