package br.com.javaskewb.Patterns.NS.L2L.LC.L4C;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Matrices.MatrixSwap;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.L3C.L3CCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;

public class L4CCases extends Cases<L4CCase> {
    private static final ArrayList<L4CCase> cases = new ArrayList<>();
    private static final int[][] facesMatrix = LCCase.getFacesMatrix();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.add(H());
            cases.add(Z());
            cases.add(TS());
            cases.add(Z1());
            cases.add(Z2());
        }
    }

    public L4CCase H(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(3, 5);
        centers.swap(5, 3);
        centers.swap(2, 6);
        centers.swap(6, 2);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("H", centersFaces);
    }

    public L4CCase Z(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(2, 3);
        centers.swap(3, 2);
        centers.swap(5, 6);
        centers.swap(6, 5);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("Z", centersFaces);
    }

    public L4CCase TS(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 1);
        centers.swap(3, 5);
        centers.swap(5, 3);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("Triple S", centersFaces);
    }

    public L4CCase Z1(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 1);
        centers.swap(3, 6);
        centers.swap(6, 3);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("Z1", centersFaces);
    }

    public L4CCase Z2(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 1);
        centers.swap(5, 6);
        centers.swap(6, 5);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("Z2", centersFaces);
    }

    @Override
    public ArrayList<L4CCase> getCases() {
        return cases;
    }
}
