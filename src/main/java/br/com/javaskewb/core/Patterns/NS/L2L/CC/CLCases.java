package br.com.javaskewb.core.Patterns.NS.L2L.CC;

import br.com.javaskewb.core.Patterns.Case;
import br.com.javaskewb.core.Patterns.Cases;
import br.com.javaskewb.core.Patterns.NS.L2L.LC.LCCases;

import java.util.ArrayList;


public abstract class CLCases<C extends CLCase> extends Cases<C>{
    public static boolean checkCenter(Case baseCase, int center){
        return baseCase.getCentersFaces().getCentersMatrix()[center][0] == 1;
    }
}
