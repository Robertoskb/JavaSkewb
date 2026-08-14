package br.com.javaskewb.core.Patterns.NS.L2L.LC.L4C;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCase;

import java.util.ArrayList;

public class L4CCases extends Cases<L4CCase> {
    private static final int[][] facesMatrix = LCCase.getFacesMatrix();

    private static final ArrayList<L4CCase> cases = fill();

    private static ArrayList<L4CCase> fill(){
        ArrayList<L4CCase> cases = new ArrayList<>();

        cases.add(H());
        cases.add(Z());
        cases.add(TS());
        cases.add(Z1());
        cases.add(Z2());

        return cases;
    }

    public static L4CCase H(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(3, 5);
        centers.swap(5, 3);
        centers.swap(2, 6);
        centers.swap(6, 2);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("H", centersFaces);
    }

    public static L4CCase Z(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(2, 3);
        centers.swap(3, 2);
        centers.swap(5, 6);
        centers.swap(6, 5);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("Z", centersFaces);
    }

    public static L4CCase TS(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 1);
        centers.swap(3, 5);
        centers.swap(5, 3);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("3S", centersFaces);
    }

    public static L4CCase Z1(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 1);
        centers.swap(3, 6);
        centers.swap(6, 3);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L4CCase("Z1", centersFaces);
    }

    public static L4CCase Z2(){
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
