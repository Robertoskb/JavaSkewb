package br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.L3C;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCase;

import java.util.ArrayList;

public class L3CCases extends Cases<L3CCase> {
    private static final int[][] facesMatrix = LCCase.getFacesMatrix();

    private static final ArrayList<L3CCase> cases = fill();

    public L3CCases() {
        super("L3C");
    }

    private static ArrayList<L3CCase> fill(){
        ArrayList<L3CCase> cases = new ArrayList<>();

        cases.add(U1());
        cases.add(U2());
        cases.add(U3());
        cases.add(O1());
        cases.add(O2());

        return cases;
    }

    public static L3CCase U1(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(2, 5);
        centers.swap(5, 6);
        centers.swap(6, 2);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L3CCase("U1", centersFaces);
    }

    public static L3CCase U2(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(2, 6);
        centers.swap(6, 5);
        centers.swap(5, 2);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L3CCase("U2", centersFaces);
    }

    public static L3CCase U3(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 6);
        centers.swap(6, 1);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L3CCase("U3", centersFaces);
    }

    public static L3CCase O1(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 5);
        centers.swap(5, 1);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L3CCase("O1", centersFaces);
    }

    public static L3CCase O2(){
        MatrixSwap centers;

        centers = new MatrixSwap(Moves.getCenterMatrix());

        centers.swap(1, 2);
        centers.swap(2, 3);
        centers.swap(3, 1);

        CentersFaces centersFaces = new CentersFaces(centers.getMatrix(), facesMatrix);

        return new L3CCase("O2", centersFaces);
    }

    @Override
    public ArrayList<L3CCase> getCases() {
        return cases;
    }
}
