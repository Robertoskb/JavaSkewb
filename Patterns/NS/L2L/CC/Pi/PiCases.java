package br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi;


import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.CL.*;

import java.util.ArrayList;

public class PiCases extends Cases<PiCase> {
    private static final PiCLCases piUCase = new PiUCases();
    private static final PiCLCases piFRCases = new PiFRCases();
    private static final PiCLCases piFLCases = new PiFLCases();
    private static final PiCLCases piBLCases = new PiBLCases();
    private static final PiCLCases piBRCases = new PiBRCases();

    protected static final ArrayList<PiCase> cases = fill();

    private static ArrayList<PiCase> fill(){
        ArrayList<PiCase> cases = new ArrayList<>();

        cases.add(new PiCase("Pi", new CentersFaces(Moves.getCenterMatrix(), PiCase.getFaces())));
        cases.addAll(piUCase.getCases());
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
