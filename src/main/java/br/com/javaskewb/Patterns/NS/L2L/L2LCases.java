package br.com.javaskewb.Patterns.NS.L2L;

import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L3C.L3CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L4C.L4CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L5C.L5CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;
import br.com.javaskewb.Patterns.NS.L2L.Peanut.PeanutCases;
import br.com.javaskewb.Patterns.NS.L2L.Pi.PiCases;

import java.util.ArrayList;

public class L2LCases extends Cases<L2LCase> {
    public static final ArrayList<L2LCase> cases = new ArrayList<>();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.addAll(new LCCases().getCases());

            cases.addAll(new PeanutCases().getCases());
            cases.addAll(new PiCases().getCases());
        }
    }

    @Override
    public ArrayList<L2LCase> getCases() {
        return cases;
    }
}
