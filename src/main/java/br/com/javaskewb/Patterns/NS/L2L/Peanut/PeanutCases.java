package br.com.javaskewb.Patterns.NS.L2L.Peanut;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Case;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L3C.L3CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L4C.L4CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L5C.L5CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class PeanutCases extends Cases<PeanutCase> {
    protected static final ArrayList<PeanutCase> cases =  new ArrayList<>();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.add(new PeanutCase("Pure Pi", new CentersFaces(Moves.getCenterMatrix(), PeanutCase.faces)));
            fill(new LCCases());
        }
    }

    private void fill(Cases<?> centerCases){
        for (Case baseCase : centerCases.getCases()){
            for (Case subCase: baseCase.getCasesVariants()){
                CentersFaces centersFaces;

                int[][] centersMatrix = subCase.getCentersFaces().getCentersMatrix();

                centersFaces = new CentersFaces(centersMatrix, PeanutCase.faces);

                cases.add(new PeanutCase("Peanut + " + subCase.getName(), centersFaces));
            }
        }
    }

    @Override
    public ArrayList<PeanutCase> getCases() {
        return cases;
    }
}
