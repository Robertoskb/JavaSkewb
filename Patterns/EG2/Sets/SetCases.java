package br.com.javaskewb.core.Patterns.EG2.Sets;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.L2LCase;
import br.com.javaskewb.core.Patterns.NS.L2L.L2LCases;

import java.util.ArrayList;

public class SetCases extends Cases<SetCase> {
    private static final L2LCases l2LCases = new L2LCases();
    private static final ArrayList<SetCase> cases = fill();

    public SetCases() {
        super("Sets");
    }

    private static ArrayList<SetCase> fill(){
        ArrayList<SetCase> cases = new ArrayList<>();

        for (L2LCase l2LCase: l2LCases.getCases())
            cases.add(new SetCase(l2LCase));

        return cases;
    }

    @Override
    public ArrayList<SetCase> getCases() {
        return cases;
    }
}
