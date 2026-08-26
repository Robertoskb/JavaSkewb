package br.com.javaskewb.core.Mapping.Moves;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;
import br.com.javaskewb.core.Mapping.Moves.Matrices.MatrixSwap;
import br.com.javaskewb.core.Cube.State;

import java.util.HashMap;

public class WCAMoves extends Moves {
    private static final HashMap<String, CentersFaces> notation = fill();

    public WCAMoves() {
        super();
    }
    public WCAMoves(State state, boolean updateState) {
        super(state, updateState);
    }

    private static HashMap<String, CentersFaces> fill() {
        HashMap<String, CentersFaces> notation = new HashMap<>();
        HashMap<String, CentersFaces> advancedMovesNotation = new AdvancedMoves().getNotation();

        String[] baseMoses = {"r", "r'", "l", "l'", "B", "B'", "b", "b'"};
        String[] wcaMoses = {"R", "R'", "L", "L'", "U", "U'", "B", "B'"};

        for (int i = 0; i < 8; i++) {
            notation.put(wcaMoses[i], advancedMovesNotation.get(baseMoses[i]));
        }

        return notation;
    }



    @Override
    public HashMap<String, CentersFaces> getNotation() {
        return notation;
    }
}