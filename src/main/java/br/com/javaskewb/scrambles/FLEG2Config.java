package br.com.javaskewb.scrambles;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.Methods.EG2.EG2Case;
import br.com.javaskewb.core.Patterns.Methods.EG2.EG2Cases;
import br.com.javaskewb.core.Patterns.Methods.FS.FL.FLCases;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.scrambles.base.FLMethodConfig;

import java.util.ArrayList;
import java.util.Random;


public class FLEG2Config implements FLMethodConfig {
    private EG2Case eg2Case;
    private Case flCase;
    private int perspective;

    private final Random random = new Random();

    private final EG2Cases eg2Cases = new EG2Cases();
    private final FLCases flCases = new FLCases();

    private static FLEG2Config fleg2Config;

    private FLEG2Config(){

    }

    public static FLEG2Config getInstance(){
        if (fleg2Config == null)
            fleg2Config = new FLEG2Config();
        return fleg2Config;
    }

    @Override
    public void applyCases(State state) {
        eg2Case.applyCase(state);
        flCase.applyCase(state);
    }


    @Override
    public void randomConfig(int flMoves) {
        eg2Case = eg2Cases.getRandomCase();
        ArrayList<Case> FLVariants = flCases.getRandomFLByMoves(flMoves).getCasesVariants();
        flCase = FLVariants.get(random.nextInt(0, FLVariants.size()));
        perspective = random.nextInt(0, 23);
    }

    @Override
    public void randomConfig(Case flCase) {
        eg2Case = eg2Cases.getRandomCase();
        ArrayList<Case> FLVariants = flCase.getCasesVariants();
        this.flCase = FLVariants.get(random.nextInt(0, FLVariants.size()));
        perspective = random.nextInt(0, 23);
    }


    public EG2Case getEg2Case() {
        return eg2Case;
    }

    public Case getFlCase() {
        return flCase;
    }

    public int getPerspective() {
        return perspective;
    }

    @Override
    public String toString() {
        return "EG2";
    }
}