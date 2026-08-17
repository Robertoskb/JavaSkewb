package br.com.javaskewb.core.Patterns.Methods.EG2;

import br.com.javaskewb.core.Patterns.base.Cases;

import java.util.ArrayList;

public class EG2SubCases extends Cases<EG2Case>{
    private final ArrayList<Cases<?>> subCases = new ArrayList<>();
    private final ArrayList<EG2Case> NS_CASES = new ArrayList<>();

    public EG2SubCases(Cases<?> cases) {
        super(cases.getName());

        for (var nsCase: cases.getCases())
            this.NS_CASES.add(new EG2Case(nsCase));

        for (Cases<?> subcases: cases.getSubCases())
            subCases.add(new EG2SubCases(subcases));

    }

    @Override
    public ArrayList<EG2Case> getCases() {
        return NS_CASES;
    }

    @Override
    public ArrayList<Cases<?>> getSubCases() {
        return subCases;
    }
}
