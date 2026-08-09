package br.com.javaskewb.core.Patterns.NS.L2L.CC;

import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.PeanutCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.PiCases;

import java.util.ArrayList;

public class CCCases extends Cases<CCCase> {
    private static final ArrayList<CCCase> cases = new ArrayList<>();
    private static final PiCases PiCases = new PiCases();
    private static final PeanutCases PeanutCases = new PeanutCases();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.addAll(PiCases.getCases());
            cases.addAll(PeanutCases.getCases());
        }
    }

    @Override
    public ArrayList<CCCase> getCases() {
        return cases;
    }
}
