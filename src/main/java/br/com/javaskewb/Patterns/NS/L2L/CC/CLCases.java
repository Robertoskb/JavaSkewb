package br.com.javaskewb.Patterns.NS.L2L.CC;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;
import br.com.javaskewb.Patterns.Case;
import br.com.javaskewb.Patterns.Cases;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCase;
import br.com.javaskewb.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;


public abstract class CLCases<C extends CLCase> extends Cases<C>{
    protected abstract ArrayList<C> fill(String name, LCCases centerCases);

    public boolean checkCenter(Case baseCase){
        return baseCase.getCentersFaces().getCentersMatrix()[getCenter()][0] == 1;
    }

    public abstract int getCenter();
    public abstract int[][] getFacesMatrix();
}
