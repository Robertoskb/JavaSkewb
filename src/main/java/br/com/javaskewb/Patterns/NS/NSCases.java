package br.com.javaskewb.Patterns.NS;

import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.L2LCases;

import java.util.ArrayList;

public class NSCases extends Cases<NSCase> {
    private static final ArrayList<NSCase> cases = new ArrayList<>();
    private static final L2LCases L2LCases = new L2LCases();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.addAll(L2LCases.getCases());
        }
    }

    @Override
    public ArrayList<NSCase> getCases() {
        return cases;
    }



}
