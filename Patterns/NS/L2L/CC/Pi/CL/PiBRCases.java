package br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PiBRCases extends PiCLCases{
    private static final ArrayList<PiCase> cases = fill();

    private static ArrayList<PiCase> fill(){
        return fill("Pi BR", new LCCases(), 4);
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }
}
