package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.CL;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.base.CLCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.Pi.PiCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCase;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCases;

import java.util.ArrayList;
import java.util.List;

public abstract class PiCLCases extends CLCases<PiCase> {
    private static final List<ArrayList<PiCase>> clCases = fillCL();

    public PiCLCases(String name) {
        super(name);
    }

    public static int[][] getFacesMatrix() {
        return PiCase.getFaces();
    }

    protected static ArrayList<PiCase> fill(int center) {
        return clCases.get(center);
    }

    public static List<ArrayList<PiCase>> fillCL(){
        List<ArrayList<PiCase>> clCases = new ArrayList<>();
        String[] names = {"U", "FR", "FL", "", "BR", "BL"};

        for (int i = 0; i < 6; i++) {
            clCases.add(new ArrayList<>());
        }

        for (LCCase baseCase: new LCCases().getCases()){
            for (Case subCase: baseCase.getCasesVariants()) {
                int centerPos = getCenterPos(subCase);
                ArrayList<PiCase> cases = clCases.get(centerPos);
                int[][] centersMatrix = subCase.getCentersFaces().getCentersMatrix();

                CentersFaces centersFaces = new CentersFaces(centersMatrix, getFacesMatrix());

                cases.add(new PiCase("Pi " + names[centerPos] + " " + subCase.getName(), centersFaces));
            }
        }

        return clCases;
    }
}
