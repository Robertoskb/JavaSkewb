package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.CL;

import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PeanutFLCases extends PeanutCLCases{
    private static final ArrayList<PeanutCase> cases = fill();

    public PeanutFLCases() {
        super("FL");
    }

    private static ArrayList<PeanutCase> fill(){
        return fill("Peanut FL", new LCCases(), 2);
    }

    @Override
    public ArrayList<PeanutCase> getCases() {
        return cases;
    }
}