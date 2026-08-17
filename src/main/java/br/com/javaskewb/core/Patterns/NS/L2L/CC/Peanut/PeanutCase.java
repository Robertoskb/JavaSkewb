package br.com.javaskewb.core.Patterns.NS.L2L.CC.Peanut;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.base.CLCase;

public class PeanutCase extends CLCase {
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

    public static int[][] getFaces() {
        return faces;
    }
}
