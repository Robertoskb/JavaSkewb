package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.CL;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PeanutUCases extends PeanutCLCases{
    private static final ArrayList<PeanutCase> cases = fill();

    public PeanutUCases() {
        super("U");
    }

    private static ArrayList<PeanutCase> fill(){
        ArrayList<PeanutCase> peanutU = fill(0);
        peanutU.addFirst(new PeanutCase("Peanut", new CentersFaces(Moves.getCenterMatrix(), PeanutCase.getFaces())));

        return peanutU;
    }

    @Override
    public ArrayList<PeanutCase> getCases() {
        return cases;
    }

}
