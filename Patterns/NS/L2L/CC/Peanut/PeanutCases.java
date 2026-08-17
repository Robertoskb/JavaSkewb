package br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut;

import java.util.ArrayList;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL.PeanutBLCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL.PeanutBRCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL.PeanutCLCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL.PeanutFLCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL.PeanutFRCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL.PeanutUCases;

public class PeanutCases extends Cases<PeanutCase> {
    private static final PeanutCLCases peanutUCases = new PeanutUCases();
    private static final PeanutCLCases peanutFRCases = new PeanutFRCases();
    private static final PeanutCLCases peanutFLCases = new PeanutFLCases();
    private static final PeanutCLCases peanutBLCases = new PeanutBLCases();
    private static final PeanutCLCases peanutBRCases = new PeanutBRCases();

    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    protected static final ArrayList<PeanutCase> cases = fill();

    public PeanutCases() {
        super("Peanut");
    }

    private static ArrayList<PeanutCase> fill(){
        ArrayList<PeanutCase> cases = new ArrayList<>();
        subCases.add(peanutUCases);
        subCases.add(peanutFRCases);
        subCases.add(peanutFLCases);
        subCases.add(peanutBLCases);
        subCases.add(peanutBRCases);

        cases.addAll(peanutUCases.getCases());
        cases.addAll(peanutFRCases.getCases());
        cases.addAll(peanutFLCases.getCases());
        cases.addAll(peanutBRCases.getCases());
        cases.addAll(peanutBLCases.getCases());

        return cases;
    }


    @Override
    public ArrayList<PeanutCase> getCases() {
        return cases;
    }

    @Override
    public ArrayList<Cases<?>> getSubCases() {
        return subCases;
    }

    public PeanutCLCases getPeanutUCase() {
        return peanutUCases;
    }

    public PeanutCLCases getPeanutFRCases() {
        return peanutFRCases;
    }

    public PeanutCLCases getPeanutFLCases() {
        return peanutFLCases;
    }

    public PeanutCLCases getPeanutBLCases() {
        return peanutBLCases;
    }

    public PeanutCLCases getPeanutBRCases() {
        return peanutBRCases;
    }
}
