package br.com.javaskewb.core.Mapping.Moves;

import br.com.javaskewb.core.Mapping.Moves.Matrices.CentersFaces;

import java.util.HashMap;

public class FLMoves extends Moves{
    private static final HashMap<String, CentersFaces> notation = new HashMap<>();

    @Override
    protected void fill() {
        if (notation.isEmpty()){
            AdvancedMoves advancedMoves = new AdvancedMoves();

            String[] moves = {"R", "R'", "F", "F'", "L", "L'", "B", "B'"};

            for (String move: moves)
                notation.put(move, advancedMoves.getMove(move));
        }
    }

    @Override
    public HashMap<String, CentersFaces> getNotation() {
        return notation;
    }
}
