package br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PiBLCases extends PiCLCases{
    private static final ArrayList<PiCase> cases = new ArrayList<>();

    @Override
    public int getCenter() {
        return 5;
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
