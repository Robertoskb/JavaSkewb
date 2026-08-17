package br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.base.CLCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public abstract class PiCLCases extends CLCases<PiCase> {
    public PiCLCases(String name) {
        super(name);
    }

    public static int[][] getFacesMatrix() {
        return PiCase.getFaces();
    }

    protected static ArrayList<PiCase> fill(String name, LCCases centerCases, int center) {
        ArrayList<PiCase> cases = new ArrayList<>();

        for (LCCase baseCase : centerCases.getCases()){
            for (Case subCase: baseCase.getCasesVariants()){
                CentersFaces centersFaces;

                if (checkCenter(subCase, center)){
                    int[][] centersMatrix = subCase.getCentersFaces().getCentersMatrix();

                    centersFaces = new CentersFaces(centersMatrix, getFacesMatrix());

                    cases.add(new PiCase(name + " " + subCase.getName(), centersFaces));
                }
            }
        }

        return cases;
    }
}
