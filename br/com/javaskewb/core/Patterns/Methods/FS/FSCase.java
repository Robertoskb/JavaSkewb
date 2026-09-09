package br.com.javaskewb.core.Patterns.Methods.FS;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Moves;
import br.com.javaskewb.core.Patterns.base.Case;

import java.util.ArrayList;

public class FSCase extends Case {
    public FSCase(String name, CentersFaces centersFaces) {
        super(name, centersFaces);
    }

    @Override
    public ArrayList<State> getStatesVariants(State initialState) {
        return null;
    }

    @Override
    public ArrayList<Case> getCasesVariants() {
        ArrayList<Case> cases = new ArrayList<>();

        String[] movesSequence = {"y", "y'", "y2"};
        String[] movesInverseSequence = {"y'", "y", "y2"};
        String[] names = {"₂", "₃", "₄"};

        cases.add(new FSCase(name + " ₁", centersFaces));
        for (int i = 0; i < 3; i++){
            String move = movesSequence[i];
            String inverseMove = movesInverseSequence[i];

            CentersFaces baseCentersFaces = Moves.mulCenterFaces(centersFaces, moves.getMove(move));
            CentersFaces newCenterFaces = Moves.mulCenterFaces(moves.getMove(inverseMove), baseCentersFaces);

            FSCase newCase = new FSCase(name + " " + names[i], newCenterFaces);

            if (!cases.contains(newCase))
                cases.add(newCase);
        }

        if (cases.size() == 1)
            cases.getFirst().setName(name);

        return cases;
    }
}
