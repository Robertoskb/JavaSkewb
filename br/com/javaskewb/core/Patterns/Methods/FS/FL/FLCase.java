package br.com.javaskewb.core.Patterns.Methods.FS.FL;

import br.com.javaskewb.core.Cube.Skewb;
import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Patterns.Methods.FS.FSCase;

public class FLCase extends FSCase {
    public FLCase(String name, CentersFaces centersFaces) {
        super("FL " + name, centersFaces);
    }

    @Override
    public Skewb getCaseSkewb() {
        State solvedState = State.getSolvedState();
        solvedState.maskLayer(3);

        State state = getCaseState(solvedState);

        Skewb skewb = new Skewb(state, solvedState);
        skewb.toAdvanced();

        return skewb;
    }
}
