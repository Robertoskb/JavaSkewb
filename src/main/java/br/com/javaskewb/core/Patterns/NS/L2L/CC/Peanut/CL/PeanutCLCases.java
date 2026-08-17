package br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.CL;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.base.CLCases;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCase;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public abstract class PeanutCLCases extends CLCases<PeanutCase> {
    public PeanutCLCases(String name) {
        super(name);
    }

    public static int[][] getFacesMatrix() {
        return PeanutCase.getFaces();
    }

    protected static ArrayList<PeanutCase> fill(String name, LCCases centerCases, int center) {
        ArrayList<PeanutCase> cases = new ArrayList<>();

        for (LCCase baseCase : centerCases.getCases()){
            for (Case subCase: baseCase.getCasesVariants()){
                CentersFaces centersFaces;

                if (checkCenter(subCase, center)){
                    int[][] centersMatrix = subCase.getCentersFaces().getCentersMatrix();

                    centersFaces = new CentersFaces(centersMatrix, getFacesMatrix());

                    cases.add(new PeanutCase(name + " " + subCase.getName(), centersFaces));
                }
            }
        }

        return cases;
    }
}
