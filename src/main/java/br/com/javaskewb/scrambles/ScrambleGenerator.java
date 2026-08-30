package br.com.javaskewb.scrambles;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Methods.EG2.EG2Cases;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.Methods.FS.FL.FLCase;
import br.com.javaskewb.core.Patterns.Methods.FS.FL.FLCases;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCase;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCases;
import br.com.javaskewb.core.Solution.Solution;
import br.com.javaskewb.core.Solution.utils.Scramble;
import br.com.javaskewb.scrambles.base.FLMethodConfig;

import java.util.ArrayList;
import java.util.Random;

public class ScrambleGenerator {
    private FLMethodConfig flMethodConfig = FLNSConfig.getInstance();

    public StateConfig randomConfig(int flMoves){
        flMethodConfig.randomConfig(flMoves);

        State state = State.getPerspective(flMethodConfig.getPerspective());

        return new StateConfig(state, flMethodConfig);
    }

    public StateConfig randomConfig(Case flCase){
        flMethodConfig.randomConfig(flCase);

        State state = State.getPerspective(flMethodConfig.getPerspective());

        return new StateConfig(state, flMethodConfig);
    }

    public void setFlMethodConfig(FLMethodConfig flMethodConfig) {
        this.flMethodConfig = flMethodConfig;
    }

    public static void main(String[] args) {
    }
}
