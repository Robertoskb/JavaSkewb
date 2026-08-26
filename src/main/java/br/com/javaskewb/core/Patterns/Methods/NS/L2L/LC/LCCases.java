package br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.L3C.L3CCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.L4C.L4CCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.L5C.L5CCases;

import java.util.ArrayList;
import java.util.List;

public class LCCases extends Cases<LCCase> {
    private static final L3CCases L3CCases = new L3CCases();
    private static final L4CCases L4CCases = new L4CCases();
    private static final L5CCases L5CCases = new L5CCases();

    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    private static final ArrayList<LCCase> cases = fill();

    public LCCases() {
        super("LC");
    }

    private static ArrayList<LCCase> fill(){
        ArrayList<LCCase> cases = new ArrayList<>();

        subCases.add(L3CCases);
        subCases.add(L4CCases);
        subCases.add(L5CCases);

        cases.addAll(L3CCases.getCases());
        cases.addAll(L4CCases.getCases());
        cases.addAll(L5CCases.getCases());

        return cases;
    }

    @Override
    public ArrayList<LCCase> getCases() {
        return cases;
    }

    @Override
    public List<Cases<?>> getSubCases() {
        return subCases;
    }

    public L3CCases getL3CCases() {
        return L3CCases;
    }

    public L4CCases getL4CCases() {
        return L4CCases;
    }

    public L5CCases getL5CCases() {
        return L5CCases;
    }
}
