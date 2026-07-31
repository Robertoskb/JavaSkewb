package br.com.javaskewb.Cube;

import br.com.javaskewb.Mapping.Solve.AdvancedMoves;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Mapping.Solve.WCAMoves;
import br.com.javaskewb.Mapping.State;

import java.util.ArrayList;
import java.util.Arrays;

public class Skewb {
    private State state;
    private Moves moves;
    private WCAMoves wcaMoves;
    private AdvancedMoves advancedMoves;

    public Skewb(){
        setState(State.getSolvedStage());
        setWcaMoves(new WCAMoves(state, true));
        setAdvancedMoves(new AdvancedMoves(state, true));
        setMoves(wcaMoves);
    }

    public Skewb(State state){
        setState(state);
        setWcaMoves(new WCAMoves(state, true));
        setAdvancedMoves(new AdvancedMoves(state, true));

        setMoves(wcaMoves);
    }

    public void toAdvanced(){
        setMoves(advancedMoves);
    }
    public void toWCA(){setMoves(wcaMoves);}

    public void applyScramble(ArrayList<String> scramble){
        for (String s: scramble)
            setState(moves.applyMove(s));
    }

    public void applyScramble(String scramble){
        applyScramble(new ArrayList<String>(Arrays.asList(scramble.split(" "))));
    }

    @Override
    public boolean equals(Object obj){
        Skewb other = (Skewb) obj;

        return state.equals(other.getState());
    }

    public Moves getMoves() {
        return moves;
    }

    public void setMoves(Moves moves) {
        this.moves = moves;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public WCAMoves getWcaMoves() {
        return wcaMoves;
    }

    public void setWcaMoves(WCAMoves wcaMoves) {
        this.wcaMoves = wcaMoves;
    }

    public AdvancedMoves getAdvancedMoves() {
        return advancedMoves;
    }

    public void setAdvancedMoves(AdvancedMoves advancedMoves) {
        this.advancedMoves = advancedMoves;
    }

    @Override
    public String toString(){
        return state.toString();
    }
}
