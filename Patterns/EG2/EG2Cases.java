package br.com.javaskewb.core.Patterns.EG2;

import br.com.javaskewb.core.Patterns.Case;
import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.EG2.Sets.SetCases;
import br.com.javaskewb.core.Patterns.NS.NSCase;

import java.util.ArrayList;

public class EG2Cases extends Cases<EG2Case> {
    private static final SetCases setCases = new SetCases();

    private static final ArrayList<EG2Case> cases = fill();



    private static ArrayList<EG2Case> fill(){

        return new ArrayList<>(setCases.getCases());
    }

    @Override
    public ArrayList<EG2Case> getCases() {
        return cases;
    }
}
