package br.com.javaskewb.Patterns.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Patterns.Case;
import br.com.javaskewb.Patterns.NS.L2L.CC.CLCase;
import br.com.javaskewb.Patterns.NS.L2L.CC.CLCases;
import br.com.javaskewb.Patterns.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public abstract class PiCLCases extends CLCases<PiCase> {
    @Override
    public int[][] getFacesMatrix() {
        return PiCase.getFaces();
    }

    @Override
    protected ArrayList<PiCase> fill(String name, LCCases centerCases) {
        ArrayList<PiCase> cases = new ArrayList<>();

        for (LCCase baseCase : centerCases.getCases()){
            for (Case subCase: baseCase.getCasesVariants()){
                CentersFaces centersFaces;

                if (checkCenter(subCase)){
                    int[][] centersMatrix = subCase.getCentersFaces().getCentersMatrix();

                    centersFaces = new CentersFaces(centersMatrix, getFacesMatrix());

                    cases.add(new PiCase(name + " " + subCase.getName(), centersFaces));
                }
            }
        }

        return cases;
    }
}
