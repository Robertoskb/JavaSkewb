package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.PeanutCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.PiCases;

import java.util.ArrayList;
import java.util.List;

public class CCCases extends Cases<CCCase> {
    private static final PiCases PiCases = new PiCases();
    private static final PeanutCases PeanutCases = new PeanutCases();
    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    private static final ArrayList<CCCase> cases = fill();

    public CCCases() {
        super("CC");
    }

    private static ArrayList<CCCase> fill(){
        ArrayList<CCCase> cases = new ArrayList<>();
        subCases.add(PiCases);
        subCases.add(PeanutCases);

        cases.addAll(PiCases.getCases());
        cases.addAll(PeanutCases.getCases());

        return cases;
    }

    @Override
    public ArrayList<CCCase> getCases() {
        return cases;
    }

    @Override
    public List<Cases<?>> getSubCases() {
        return subCases;
    }
}
