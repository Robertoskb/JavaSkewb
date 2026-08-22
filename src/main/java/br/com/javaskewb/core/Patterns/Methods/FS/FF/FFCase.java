package br.com.javaskewb.core.Patterns.Methods.FS.FF;

import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.Methods.FS.FSCase;

public class FFCase extends FSCase {
    public FFCase(String name, CentersFaces centersFaces) {
        super("FF " + name, centersFaces);
    }

    @Override
    public Skewb getCaseSkewb() {
        State solvedState = State.getSolvedState();
        solvedState.maskFace(3);

        State state = getCaseState(solvedState);

        Skewb skewb = new Skewb(state, solvedState);
        skewb.toAdvanced();

        return skewb;
    }
}
