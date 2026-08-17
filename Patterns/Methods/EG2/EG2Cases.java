package br.com.javaskewb.core.Patterns.Methods.EG2;

import br.com.javaskewb.core.Patterns.Methods.NS.L2L.L2LCase;
import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.EG2.Sets.SetCases;

import java.util.ArrayList;

public class EG2Cases extends Cases<EG2Case> {
    private static final SetCases setCases = new SetCases();

    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    private static final ArrayList<EG2Case> cases = fill();

    public EG2Cases() {
        super("EG2");
    }

    private static ArrayList<EG2Case> fill(){
        subCases.add(setCases);

        return new ArrayList<>(setCases.getCases());
    }

    @Override
    public ArrayList<EG2Case> getCases() {
        return cases;
    }

    @Override
    public ArrayList<Cases<?>> getSubCases() {
        return subCases;
    }
}
