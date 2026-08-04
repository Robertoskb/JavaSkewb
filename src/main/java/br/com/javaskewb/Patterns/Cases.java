package br.com.javaskewb.Patterns;

import br.com.javaskewb.Mapping.Solve.Matrices.CentersFaces;

import java.util.ArrayList;

public abstract class Cases<C extends Case> {

    public Cases(){
        fillCases();
    }

    public abstract void fillCases();

    public abstract ArrayList<C> getCases();
}
