package br.com.javaskewb.Patterns.NS.L2L.LC;

import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L3C.L3CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L4C.L4CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L5C.L5CCases;

import java.util.ArrayList;

public class LCCases extends Cases<LCCase> {
    protected static final ArrayList<LCCase> cases = new ArrayList<>();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.addAll(new L3CCases().getCases());
            cases.addAll(new L4CCases().getCases());
            cases.addAll(new L5CCases().getCases());
        }
    }

    @Override
    public ArrayList<LCCase> getCases() {
        return cases;
    }
}
