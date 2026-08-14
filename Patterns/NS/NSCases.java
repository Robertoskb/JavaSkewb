package br.com.javaskewb.core.Patterns.NS;

import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.FL.FLCases;
import br.com.javaskewb.core.Patterns.NS.L2L.L2LCases;

import java.util.ArrayList;

public class NSCases extends Cases<NSCase> {
    private static final L2LCases L2LCases = new L2LCases();
    private static final FLCases FlCases = new FLCases();

    private static final ArrayList<NSCase> cases = fill();

    private static ArrayList<NSCase> fill(){

        return new ArrayList<>(L2LCases.getCases());
    }

    @Override
    public ArrayList<NSCase> getCases() {
        return cases;
    }

    public FLCases getFlCases() {
        return FlCases;
    }
}
