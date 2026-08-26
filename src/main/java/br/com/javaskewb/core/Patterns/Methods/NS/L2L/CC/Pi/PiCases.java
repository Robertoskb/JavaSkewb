package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi;


import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.CL.*;
import br.com.javaskewb.core.Patterns.base.Cases;

import java.util.ArrayList;
import java.util.List;

public class PiCases extends Cases<PiCase> {
    private static final PiCLCases piUCases = new PiUCases();
    private static final PiCLCases piFRCases = new PiFRCases();
    private static final PiCLCases piFLCases = new PiFLCases();
    private static final PiCLCases piBLCases = new PiBLCases();
    private static final PiCLCases piBRCases = new PiBRCases();

    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    protected static final ArrayList<PiCase> cases = fill();

    public PiCases() {
        super("Pi");
    }

    private static ArrayList<PiCase> fill(){
        ArrayList<PiCase> cases = new ArrayList<>();

        subCases.add(piUCases);
        subCases.add(piFRCases);
        subCases.add(piFLCases);
        subCases.add(piBRCases);
        subCases.add(piBLCases);

        cases.addAll(piUCases.getCases());
        cases.addAll(piFRCases.getCases());
        cases.addAll(piFLCases.getCases());
        cases.addAll(piBRCases.getCases());
        cases.addAll(piBLCases.getCases());

        return cases;
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }

    @Override
    public List<Cases<?>> getSubCases() {
        return subCases;
    }

    public PiCLCases getPiUCase() {
        return piUCases;
    }

    public PiCLCases getPiFRCases() {
        return piFRCases;
    }

    public PiCLCases getPiFLCases() {
        return piFLCases;
    }

    public PiCLCases getPiBLCases() {
        return piBLCases;
    }

    public PiCLCases getPiBRCases() {
        return piBRCases;
    }
}
