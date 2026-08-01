package br.com.javaskewb.Solution.utils;

import br.com.javaskewb.Mapping.State;

import java.util.ArrayList;

public class StateNode {
    private State state;
    private int distance;
    private ArrayList<String> scramble = new ArrayList<>();

    public StateNode(State state, int distance){
        setState(state);
        setDistance(distance);
    }

    public StateNode(State state, int distance, ArrayList<String> scramble){
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

    public ArrayList<String> getScramble() {
        return scramble;
    }

    public void setScramble(ArrayList<String> scramble) {
        this.scramble = scramble;
    }

}
