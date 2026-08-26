package br.com.javaskewb.scrambles.base;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Patterns.base.Case;

import java.util.ArrayList;

public abstract class Config {
    protected ArrayList<Case> cases = new ArrayList<>();

    public void applyCases(State state){
        for (Case c: cases)
            c.applyCase(state);
    }
}
