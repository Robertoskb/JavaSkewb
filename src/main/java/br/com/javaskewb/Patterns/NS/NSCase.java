package br.com.javaskewb.Patterns.NS;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Mapping.State;
import br.com.javaskewb.Patterns.Case;

import java.util.ArrayList;

public abstract class NSCase extends Case {
    public NSCase(String name, CentersFaces centersFaces) {
        super(name, centersFaces);
    }

    @Override
    public ArrayList<State> getVariants(State initialState) {
        ArrayList<State> states = new ArrayList<>();

        String[] movesSequence = {"y", "y'", "y2"};
        String[] movesInverseSequence = {"y'", "y", "y2"};

        states.add(Moves.move(initialState, centersFaces));
        for (int i = 0; i < 3; i++){
            String move = movesSequence[i];
            String inverseMove = movesInverseSequence[i];

            State baseState = Moves.move(initialState, moves.getMove(move));
            State intermediareState = Moves.move(baseState, centersFaces);
            State newState = Moves.move(intermediareState, moves.getMove(inverseMove));

            if (!states.contains(newState))
                states.add(newState);
        }

        return states;
    }
}
