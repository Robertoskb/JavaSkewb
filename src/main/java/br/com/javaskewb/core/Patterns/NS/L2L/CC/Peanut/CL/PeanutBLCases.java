package br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL;

import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PeanutBLCases extends PeanutCLCases{
    private static final ArrayList<PeanutCase> cases = fill();

    private static ArrayList<PeanutCase> fill(){
        return fill("Peanut BL", new LCCases(), 5);
    }

    @Override
    public ArrayList<PeanutCase> getCases() {
        return cases;
    }

}