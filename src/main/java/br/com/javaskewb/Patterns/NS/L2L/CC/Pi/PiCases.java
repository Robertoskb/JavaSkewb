package br.com.javaskewb.Patterns.NS.L2L.CC.Pi;


import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.CC.CLCase;
import br.com.javaskewb.Patterns.NS.L2L.CC.Pi.CL.*;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCase;

import java.util.ArrayList;

public class PiCases extends Cases<PiCase> {
    protected static final ArrayList<PiCase> cases =  new ArrayList<>();
    private static final PiCLCases piUCase = new PiUCases();
    private static final PiCLCases piFRCases = new PiFRCases();
    private static final PiCLCases piFLCases = new PiFLCases();
    private static final PiCLCases piBLCases = new PiBLCases();
    private static final PiCLCases piBRCases = new PiBRCases();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.add(new PiCase("", new CentersFaces(Moves.getCenterMatrix(), PiCase.getFaces())));
            cases.addAll(piUCase.getCases());
            cases.addAll(piFRCases.getCases());
            cases.addAll(piFLCases.getCases());
            cases.addAll(piBRCases.getCases());
            cases.addAll(piBLCases.getCases());
        }
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }

    public PiCLCases getPiUCase() {
        return piUCase;
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
