package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PiBRCases extends PiCLCases{
    private static final ArrayList<PiCase> cases = fill();

    public PiBRCases() {
        super("BR");
    }

    private static ArrayList<PiCase> fill(){
        return fill(4);
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }
}
