package br.com.javaskewb.Patterns.NS.L2L;

import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;
import br.com.javaskewb.Patterns.NS.L2L.CC.CCCases;

import java.util.ArrayList;

public class L2LCases extends Cases<L2LCase> {
    private static final ArrayList<L2LCase> cases = new ArrayList<>();
    private static final LCCases LCCases = new LCCases();
    private static final CCCases CCCases = new CCCases();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.addAll(LCCases.getCases());
            cases.addAll(CCCases.getCases());
        }
    }

    @Override
    public ArrayList<L2LCase> getCases() {
        return cases;
    }

    public LCCases getLCCases() {
        return LCCases;
    }

    public CCCases getZBLLCases() {
        return CCCases;
    }
}
