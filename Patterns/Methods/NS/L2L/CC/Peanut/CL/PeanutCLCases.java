package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.CL;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.base.CLCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Peanut.PeanutCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCases;

import java.util.ArrayList;
import java.util.List;

public abstract class PeanutCLCases extends CLCases<PeanutCase> {
    private static final List<ArrayList<PeanutCase>> clCases = fillCL();

    public PeanutCLCases(String name) {
        super("Peanut " + name);
    }

    public static int[][] getFacesMatrix() {
        return PeanutCase.getFaces();
    }

    protected static ArrayList<PeanutCase> fill(int center) {
        return clCases.get(center);
    }

    public static List<ArrayList<PeanutCase>> fillCL(){
        List<ArrayList<PeanutCase>> clCases = new ArrayList<>();
        String[] names = {"U", "FR", "FL", "", "BR", "BL"};

        for (int i = 0; i < 6; i++) {
            clCases.add(new ArrayList<>());
        }

        for (LCCase baseCase: new LCCases().getCases()){
            for (Case subCase: baseCase.getCasesVariants()) {
                int centerPos = getCenterPos(subCase);
                ArrayList<PeanutCase> cases = clCases.get(centerPos);
                int[][] centersMatrix = subCase.getCentersFaces().getCentersMatrix();

                CentersFaces centersFaces = new CentersFaces(centersMatrix, getFacesMatrix());

            cases.add(new PeanutCase("Peanut " + names[centerPos] + " " + subCase.getName(), centersFaces));
            }
        }

        return clCases;
    }
}
