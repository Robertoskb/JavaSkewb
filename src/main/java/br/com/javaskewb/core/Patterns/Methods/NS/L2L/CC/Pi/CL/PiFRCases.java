package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PiFRCases extends PiCLCases {
    private static final ArrayList<PiCase> cases = fill();

    public PiFRCases() {
        super("FR");
    }

    private static ArrayList<PiCase> fill(){
        return fill("Pi FR", new LCCases(), 1);
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }

}