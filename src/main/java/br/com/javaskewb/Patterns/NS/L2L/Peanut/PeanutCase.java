package br.com.javaskewb.Patterns.NS.L2L.Peanut;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Matrices.MatrixSwap;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Case;
import br.com.javaskewb.Patterns.NS.L2L.L2LCase;

public class PeanutCase extends L2LCase {
    protected final static int[][] faces = getPeanutMatrix();

    public PeanutCase(String name, CentersFaces centersFaces) {
        super(name, centersFaces);
    }

    private static int[][] getPeanutMatrix(){
        MatrixSwap matrixSwap = new MatrixSwap(Moves.getFacesMatrix());

        matrixSwap.swap(10, 12);
        matrixSwap.swap(12, 11);
        matrixSwap.swap(11, 10);

        matrixSwap.swap(4,5);
        matrixSwap.swap(5,6);
        matrixSwap.swap(6,4);

        return matrixSwap.getMatrix();
    }
}
