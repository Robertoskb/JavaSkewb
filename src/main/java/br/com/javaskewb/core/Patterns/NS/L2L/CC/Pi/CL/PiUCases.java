package br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PiUCases extends PiCLCases {
    private static final ArrayList<PiCase> cases = fill();

    public PiUCases() {
        super("U");
    }

    private static ArrayList<PiCase> fill(){
        ArrayList<PiCase> piU = fill("Pi U", new LCCases(), 0);
        piU.addFirst(new PiCase("Pi", new CentersFaces(Moves.getCenterMatrix(), PiCase.getFaces())));
        return piU;
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }
}
