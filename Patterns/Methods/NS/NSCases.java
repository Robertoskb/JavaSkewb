package br.com.javaskewb.core.Patterns.Methods.NS;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.FS.FL.FLCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.L2LCases;

import java.util.ArrayList;

public class NSCases extends Cases<NSCase> {
    private static final L2LCases L2LCases = new L2LCases();
    private static final FLCases FlCases = new FLCases();
    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    private static final ArrayList<NSCase> cases = fill();


    public NSCases() {
        super("NS");
    }

    private static ArrayList<NSCase> fill(){
        subCases.add(L2LCases);

        return new ArrayList<>(L2LCases.getCases());
    }

    @Override
    public ArrayList<NSCase> getCases() {
        return cases;
    }

    @Override
    public ArrayList<Cases<?>> getSubCases() {
        return subCases;
    }

    public FLCases getFlCases() {
        return FlCases;
    }
}
