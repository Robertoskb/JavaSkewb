package br.com.javaskewb.core.Patterns.NS.L2L.CC;

import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.PeanutCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.PiCases;

import java.util.ArrayList;

public class CCCases extends Cases<CCCase> {
    private static final PiCases PiCases = new PiCases();
    private static final PeanutCases PeanutCases = new PeanutCases();

    private static final ArrayList<CCCase> cases = fill();

    private static ArrayList<CCCase> fill(){
        ArrayList<CCCase> cases = new ArrayList<>();
        cases.addAll(PiCases.getCases());
        cases.addAll(PeanutCases.getCases());

        return cases;
    }

    @Override
    public ArrayList<CCCase> getCases() {
        return cases;
    }
}
