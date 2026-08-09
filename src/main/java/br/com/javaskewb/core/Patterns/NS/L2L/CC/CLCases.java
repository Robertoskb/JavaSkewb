package br.com.javaskewb.core.Patterns.NS.L2L.CC;

import br.com.javaskewb.core.Patterns.Case;
import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;


public abstract class CLCases<C extends CLCase> extends Cases<C>{
    protected abstract ArrayList<C> fill(String name, LCCases centerCases);

    public boolean checkCenter(Case baseCase){
        return baseCase.getCentersFaces().getCentersMatrix()[getCenter()][0] == 1;
    }

    public abstract int getCenter();
    public abstract int[][] getFacesMatrix();
}
