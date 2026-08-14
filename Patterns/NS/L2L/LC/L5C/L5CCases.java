package br.com.javaskewb.core.Patterns.NS.L2L.LC.L5C;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCase;

import java.util.ArrayList;

public class L5CCases extends Cases<L5CCase> {
    private static final int[][] facesMatrix = LCCase.getFacesMatrix();
    private static final ArrayList<L5CCase> cases = fill();

    private static ArrayList<L5CCase> fill(){
        ArrayList<L5CCase> cases = new ArrayList<>();
        cases.add(X1());
        cases.add(X2());
        cases.add(W1());
        cases.add(W2());
        cases.add(S1());
        cases.add(S2());

        return cases;
    }

    public static L5CCase X1(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 6);
        centers.swap(3, 1);
        centers.swap(5, 3);
        centers.swap(6, 5);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L5CCase("X1", centersFaces);
    }

    public static L5CCase X2(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 6);
        centers.swap(3, 5);
        centers.swap(5, 1);
        centers.swap(6, 3);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L5CCase("X2", centersFaces);
    }

    public static L5CCase W1(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 3);
        centers.swap(3, 5);
        centers.swap(5, 6);
        centers.swap(6, 1);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L5CCase("W1", centersFaces);
    }

    public static L5CCase W2(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 5);
        centers.swap(3, 6);
        centers.swap(5, 3);
        centers.swap(6, 1);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L5CCase("W2", centersFaces);
    }

    public static L5CCase S1(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 5);
        centers.swap(3, 1);
        centers.swap(5, 6);
        centers.swap(6, 3);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L5CCase("S1", centersFaces);
    }

    public static L5CCase S2(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 3);
        centers.swap(3, 6);
        centers.swap(5, 1);
        centers.swap(6, 5);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L5CCase("S2", centersFaces);
    }

    @Override
    public ArrayList<L5CCase> getCases() {
        return cases;
    }
}
