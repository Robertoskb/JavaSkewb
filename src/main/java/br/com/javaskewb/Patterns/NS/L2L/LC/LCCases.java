package br.com.javaskewb.Patterns.NS.L2L.LC;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L3C.L3CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L4C.L4CCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.L4C.L4CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L5C.L5CCases;

import java.util.ArrayList;

public class LCCases extends Cases<LCCase> {
    private static final ArrayList<LCCase> cases = new ArrayList<>();
    private static final L3CCases L3CCases = new L3CCases();
    private static final L4CCases L4CCases = new L4CCases();
    private static final L5CCases L5CCases = new L5CCases();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.add(new LCCase("", new CentersFaces(Moves.getCenterMatrix(), Moves.getFacesMatrix())));
            cases.addAll(L3CCases.getCases());
            cases.addAll(L4CCases.getCases());
            cases.addAll(L5CCases.getCases());
        }
    }

    @Override
    public ArrayList<LCCase> getCases() {
        return cases;
    }

    public L3CCases getL3CCases() {
        return L3CCases;
    }

    public L4CCases getL4CCases() {
        return L4CCases;
    }

    public L5CCases getL5CCases() {
        return L5CCases;
    }
}
