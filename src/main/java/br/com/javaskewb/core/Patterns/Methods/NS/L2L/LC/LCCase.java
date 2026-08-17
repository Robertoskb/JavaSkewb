package br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.L2LCase;

public class LCCase extends L2LCase {
    protected static final int[][] facesMatrix = Moves.getFacesMatrix();

    public LCCase(String name, CentersFaces centersFaces) {
        super(name, centersFaces);
    }

    public static int[][] getFacesMatrix() {
        return facesMatrix;
    }
}
