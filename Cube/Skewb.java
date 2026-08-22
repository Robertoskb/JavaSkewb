package br.com.javaskewb.core.Cube;

import br.com.javaskewb.core.Mapping.Moves.AdvancedMoves;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Mapping.Moves.WCAMoves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skewb {
    private State state;
    private final State initialState;
    private Moves moves;
    private WCAMoves wcaMoves;
    private AdvancedMoves advancedMoves;
    private ArrayList<State> solvedStates;

    public Skewb(){
        setState(State.getSolvedState());
        initialState = state.cloneState();
        setWcaMoves(new WCAMoves(state, true));
        setAdvancedMoves(new AdvancedMoves(state, true));
        setMoves(wcaMoves);

        setSolvedStates(generatePerspectiveStates());
    }

    public Skewb(State baseState){
        setState(baseState);
        initialState = baseState.cloneState();
        setWcaMoves(new WCAMoves(baseState, true));
        setAdvancedMoves(new AdvancedMoves(baseState, true));
        setMoves(wcaMoves);

        setSolvedStates(generatePerspectiveStates(baseState));
    }

    public Skewb(State initialState, State solvedState){
        setState(initialState);
        this.initialState = initialState.cloneState();
        setWcaMoves(new WCAMoves(initialState, true));
        setAdvancedMoves(new AdvancedMoves(initialState, true));
        setMoves(wcaMoves);

        setSolvedStates(generatePerspectiveStates(solvedState));
    }

    public ArrayList<State> generatePerspectiveStates(State baseState){
        return State.generatePerspectivesStates(baseState);
    }

    public void maskSide(int side){
        state.maskLayer(side);

        State base = State.getSolvedState();
        base.maskLayer(side);

        setSolvedStates(generatePerspectiveStates(base));
    }

    public ArrayList<State> generatePerspectiveStates(){
        return generatePerspectiveStates(State.getSolvedState());
    }

    public boolean isSolved(){
        return solvedStates.contains(state);
    }

    public void toAdvanced(){
        setMoves(advancedMoves);
    }
    public void toWCA(){setMoves(wcaMoves);}

    public void applyScramble(List<String> scramble){
        for (String s: scramble)
            moves.applyMove(s);
    }

    public void applyScramble(String scramble){
        applyScramble(Arrays.asList(scramble.split(" ")));
    }

    @Override
    public boolean equals(Object obj){
        if (!obj.getClass().isAssignableFrom(Skewb.class))
            return false;

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
        if (wcaMoves != null || advancedMoves != null){
            setSolvedStates(generatePerspectiveStates());
            if (wcaMoves != null)
                wcaMoves.setState(state);
            if (advancedMoves != null)
                advancedMoves.setState(state);
        }
    }

    public void reset(){
        state = initialState.cloneState();
        if (wcaMoves != null || advancedMoves != null){
            if (wcaMoves != null)
                wcaMoves.setState(state);
            if (advancedMoves != null)
                advancedMoves.setState(state);
        }
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

    public ArrayList<State> getSolvedStates() {
        return solvedStates;
    }

    public void setSolvedStates(ArrayList<State> solvedStates) {
        this.solvedStates = solvedStates;
    }

    public State getInitialState() {
        return initialState;
    }
}
