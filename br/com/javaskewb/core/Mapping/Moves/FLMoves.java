package br.com.javaskewb.core.Mapping.Moves;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;

import java.util.HashMap;

public class FLMoves extends Moves{
    private static final HashMap<String, CentersFaces> notation = fill();

    private static HashMap<String, CentersFaces> fill() {
        HashMap<String, CentersFaces> notation = new HashMap<>();
        AdvancedMoves advancedMoves = new AdvancedMoves();

        String[] moves = {"R", "R'", "F", "F'", "L", "L'", "B", "B'"};

        for (String move: moves)
            notation.put(move, advancedMoves.getMove(move));

        return notation;
    }

    @Override
    public HashMap<String, CentersFaces> getNotation() {
        return notation;
    }
}
