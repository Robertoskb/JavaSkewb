package br.com.javaskewb.Patterns.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.Patterns.NS.L2L.CC.CLCase;
import br.com.javaskewb.Patterns.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PiBRCases extends PiCLCases{
    private static final ArrayList<PiCase> cases = new ArrayList<>();

    @Override
    public int getCenter() {
        return 4;
    }

    @Override
    public void fillCases() {
        if (cases.isEmpty()) {
            cases.addAll(fill("Pi", new LCCases()));
        }
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }
}
