package br.com.javaskewb.Mapping.Solve;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;

import java.util.HashMap;

public class FLMoves extends Moves{
    private static final HashMap<String, CentersFaces> notation = new HashMap<>();

    @Override
    protected void fill() {
        if (notation.isEmpty()){
            AdvancedMoves advancedMoves = new AdvancedMoves();

            String[] moves = {"F", "F'", "B", "B'", "R", "R'", "L", "L'"};

            for (String move: moves)
                notation.put(move, advancedMoves.getMove(move));
        }
    }

    @Override
    public HashMap<String, CentersFaces> getNotation() {
        return notation;
    }
}
