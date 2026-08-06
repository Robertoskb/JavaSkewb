package br.com.javaskewb.Patterns;

import br.com.javaskewb.Mapping.Solve.AdvancedMoves;
import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Mapping.State;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Case {
    protected String name;
    protected CentersFaces centersFaces;

    protected final AdvancedMoves moves = new AdvancedMoves();

    public Case(String name, CentersFaces centersFaces){
        setName(name);
        setCentersFaces(centersFaces);
    }

    public abstract ArrayList<State> getStatesVariants(State initialState);
    public abstract ArrayList<Case> getCasesVariants();

    public void applyCase(State initialState){
        Moves.move(initialState, centersFaces, true);
    }

    public CentersFaces getCentersFaces() {
        return centersFaces;
    }

    public void setCentersFaces(CentersFaces centersFaces) {
        this.centersFaces = centersFaces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object){
        if (!(object instanceof Case other))
            return false;

        return other.getCentersFaces().equals(centersFaces);
    }

    @Override
    public int hashCode(){
        return Objects.hash(centersFaces);
    }
}
