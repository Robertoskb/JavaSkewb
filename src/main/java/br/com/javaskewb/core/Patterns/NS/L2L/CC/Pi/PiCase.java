package br.com.javaskewb.core.Patterns.NS.L2L.CC.Pi;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.NS.L2L.CC.base.CLCase;

public class PiCase extends CLCase {
    protected final static int[][] faces = getPiMatrix();

    private static int[][] getPiMatrix() {
        MatrixSwap matrixSwap = new MatrixSwap(Moves.getFacesMatrix());

        matrixSwap.swap(10, 12);
        matrixSwap.swap(12, 11);
        matrixSwap.swap(11, 10);

        matrixSwap.swap(4,5);
        matrixSwap.swap(5,6);
        matrixSwap.swap(6,4);

        matrixSwap.swap(1, 3);
        matrixSwap.swap(3, 2);
        matrixSwap.swap(2, 1);

        matrixSwap.swap(7, 8);
        matrixSwap.swap(8, 9);
        matrixSwap.swap(9, 7);

        return matrixSwap.getMatrix();
    }


    public PiCase(String name, CentersFaces centersFaces) {
        super(name, centersFaces);
    }

    public static int[][] getFaces() {
        return faces;
    }
}
