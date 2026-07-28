package br.com.javaskewb.Cube;

import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Mapping.State;

import java.util.ArrayList;
import java.util.Arrays;

public class Skewb {
    private State state;
    private WCAMoves wcaMoves;

    public Skewb(){
        setState(State.getSolvedStage());
        setWcaMoves(new WCAMoves(state, true));
    }

    public Skewb(State state){
        setState(state);
        setWcaMoves(new WCAMoves(state, true));
    }

    public void applyScramble(ArrayList<String> scramble){
        for (String s: scramble)
            setState(wcaMoves.applyMove(s));
    }

    public void applyScramble(String scramble){
        applyScramble(new ArrayList<String>(Arrays.asList(scramble.split(" "))));
    }

    @Override
    public boolean equals(Object obj){
        Skewb other = (Skewb) obj;

        return state.equals(other.getState());
    }

    public WCAMoves getWcaMoves() {
        return wcaMoves;
    }

    public void setWcaMoves(WCAMoves wcaMoves) {
        this.wcaMoves = wcaMoves;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString(){
        return state.toString();
    }
}
