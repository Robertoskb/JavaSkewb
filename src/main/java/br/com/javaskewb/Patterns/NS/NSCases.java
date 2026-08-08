package br.com.javaskewb.Patterns.NS;

import br.com.javaskewb.Cube.State;
import br.com.javaskewb.Mapping.Solve.AdvancedMoves;
import br.com.javaskewb.Mapping.Solve.Moves;
import br.com.javaskewb.Patterns.Case;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.FL.FLCase;
import br.com.javaskewb.Patterns.NS.FL.FLCases;
import br.com.javaskewb.Patterns.NS.L2L.L2LCases;
import br.com.javaskewb.Solution.BFSSkewb;
import br.com.javaskewb.Solution.Solution;
import br.com.javaskewb.Solution.utils.Scramble;

import java.util.ArrayList;
import java.util.Random;

public class NSCases extends Cases<NSCase> {
    private static final ArrayList<NSCase> cases = new ArrayList<>();
    private static final L2LCases L2LCases = new L2LCases();

    @Override
    public void fillCases() {
        if (cases.isEmpty()){
            cases.addAll(L2LCases.getCases());
        }
    }

    @Override
    public ArrayList<NSCase> getCases() {
        return cases;
    }
}
