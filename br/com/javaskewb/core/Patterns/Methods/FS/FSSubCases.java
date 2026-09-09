package br.com.javaskewb.core.Patterns.Methods.FS;

import br.com.javaskewb.core.Patterns.base.Cases;

import java.util.ArrayList;

public class FSSubCases<F extends FSCase> extends Cases<F> {
    private final ArrayList<F> cases;

    public FSSubCases(String name, ArrayList<F> cases) {
        super(name);
        this.cases = cases;
    }


    @Override
    public ArrayList<F> getCases() {
        return cases;
    }

}
