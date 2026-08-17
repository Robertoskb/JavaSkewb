package br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.base;

import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.base.Cases;


public abstract class CLCases<C extends CLCase> extends Cases<C>{
    public CLCases(String name) {
        super(name);
    }

    public static boolean checkCenter(Case baseCase, int center){
        return baseCase.getCentersFaces().getCentersMatrix()[center][0] == 1;
    }
}
