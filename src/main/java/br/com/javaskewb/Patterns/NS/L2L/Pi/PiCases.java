package br.com.javaskewb.Patterns.NS.L2L.Pi;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Case;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L3C.L3CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L4C.L4CCases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L5C.L5CCases;

import java.util.ArrayList;

public class PiCases extends Cases<PiCase> {
    protected static final ArrayList<PiCase> cases =  new ArrayList<>();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.add(new PiCase("Pure Pi", new CentersFaces(Moves.getCenterMatrix(), PiCase.faces)));
            fill(new L3CCases());
            fill(new L4CCases());
            fill(new L5CCases());
        }
    }

    private void fill(Cases<?> centerCases){
        for (Case baseCase : centerCases.getCases()){
            for (Case subCase: baseCase.getCasesVariants()){
                CentersFaces centersFaces;

                int[][] centersMatrix = subCase.getCentersFaces().getCentersMatrix();

                centersFaces = new CentersFaces(centersMatrix, PiCase.faces);

                cases.add(new PiCase("Pi + " + baseCase.getName(), centersFaces));
            }
        }
    }

    @Override
    public ArrayList<PiCase> getCases() {
        return cases;
    }
}
