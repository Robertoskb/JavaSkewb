package br.com.javaskewb.Patterns.NS.L2L.LC;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.NS.L2L.L2LCase;

public class LCCase extends L2LCase {
    protected static final int[][] facesMatrix = Moves.getFacesMatrix();

    public LCCase(String name, CentersFaces centersFaces) {
        super(name, centersFaces);
    }

    public static int[][] getFacesMatrix() {
        return facesMatrix;
    }
}
