package br.com.javaskewb.Patterns.NS.L2L.LC;

import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Cases;

public abstract class LCCases<C extends LCCase> extends Cases<C> {
    protected static final int[][] facesMatrix = Moves.getFacesMatrix();
}
