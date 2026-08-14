package br.com.javaskewb.core.Patterns;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;

import java.util.ArrayList;

public class Variant extends Case {
    private final Case originalCase;
    public Variant(String name, CentersFaces centersFaces, Case originalCase) {
        super(name, centersFaces);
        this.originalCase = originalCase;
    }

    @Override
    public ArrayList<State> getStatesVariants(State initialState) {
        return null;
    }

    @Override
    public ArrayList<Case> getCasesVariants() {
        return null;
    }

    public Case getOriginalCase() {
        return originalCase;
    }
}
