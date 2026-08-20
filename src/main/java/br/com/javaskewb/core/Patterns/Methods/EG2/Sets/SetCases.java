package br.com.javaskewb.core.Patterns.Methods.EG2.Sets;

import br.com.javaskewb.core.Patterns.Methods.EG2.EG2Case;
import br.com.javaskewb.core.Patterns.Methods.EG2.EG2SubCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.CCCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.CCCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.L2LCases;
import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.L2LCase;

import java.util.ArrayList;

public class SetCases extends Cases<EG2Case> {
    private static final L2LCases l2LCases = new L2LCases();
    private static final ArrayList<EG2Case> cases = fill();
    private static final EG2SubCases subCases = new EG2SubCases(l2LCases);

    public SetCases() {
        super("Sets");
    }

    private static ArrayList<EG2Case> fill(){
        ArrayList<EG2Case> cases = new ArrayList<>();

        for (L2LCase l2LCase: l2LCases.getCases())
            cases.add(new EG2Case(l2LCase));

        return cases;
    }

    @Override
    public ArrayList<EG2Case> getCases() {
        return cases;
    }

    @Override
    public ArrayList<Cases<?>> getSubCases() {
        return subCases.getSubCases();
    }
}
