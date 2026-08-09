package br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL;

import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PeanutFLCases extends PeanutCLCases{
    private static final ArrayList<PeanutCase> cases = new ArrayList<>();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.addAll(fill("Peanut", new LCCases()));
        }
    }

    @Override
    public ArrayList<PeanutCase> getCases() {
        return cases;
    }


    @Override
    public int getCenter() {
        return 2;
    }
}