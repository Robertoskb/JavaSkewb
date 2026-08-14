package br.com.javaskewb.scrambles;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Case;
import br.com.javaskewb.core.Patterns.NS.FL.FLCase;
import br.com.javaskewb.core.Patterns.NS.FL.FLCases;
import br.com.javaskewb.core.Patterns.NS.NSCase;
import br.com.javaskewb.core.Patterns.NS.NSCases;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;

import java.util.ArrayList;
import java.util.Random;

public class ScrambleGenerator {
    private final NSCases nsCases = new NSCases();
    private final FLCases flCases = new FLCases();
    private final Solution solution = new Solution();

    Random random = new Random();

    public Scramble FLNSScramble(int flMoves){
        Scramble scramble;
        do {
            NSCase nsCase = nsCases.getRandomCase();
            FLCase flCase = flCases.getRandomFLByMoves(flMoves);
            State state = State.getRandomPerspective();

            nsCase.applyCase(state);
            flCase.applyCase(state);

            scramble = solution.findScramble(state);
        } while (scramble.size() < 7);

        return scramble;
    }

    public StateConfig randomConfig(int flMoves){
        FLNSConfig.Builder builder = new FLNSConfig.Builder();

        State state = State.getRandomPerspective();

        builder.nsCase(nsCases.getRandomCase());
        ArrayList<Case> FLVariants = flCases.getRandomFLByMoves(flMoves).getCasesVariants();
        builder.flCase(FLVariants.get(random.nextInt(0, FLVariants.size())));
        builder.perspective(state.getPerspective());

        FLNSConfig flnsConfig = builder.build();

        return new StateConfig(state, flnsConfig);
    }

    public StateConfig randomConfig(Case flCase){
        FLNSConfig.Builder builder = new FLNSConfig.Builder();

        State state = State.getRandomPerspective();

        builder.nsCase(nsCases.getRandomCase());
        ArrayList<Case> FLVariants = flCase.getCasesVariants();
        builder.flCase(FLVariants.get(random.nextInt(0, FLVariants.size())));
        builder.perspective(state.getPerspective());

        FLNSConfig flnsConfig = builder.build();

        return new StateConfig(state, flnsConfig);
    }


    public static void main(String[] args) {
        ScrambleGenerator scrambleGenerator = new ScrambleGenerator();
        for (int i = 0; i < 100; i++) {
            System.out.println(scrambleGenerator.FLNSScramble(1));
        }
    }
}
