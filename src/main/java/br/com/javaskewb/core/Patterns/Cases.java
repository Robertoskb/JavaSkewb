package br.com.javaskewb.core.Patterns;

import java.util.ArrayList;

public abstract class Cases<C extends Case> {

    public Cases(){
        fillCases();
    }

    public abstract void fillCases();

    public abstract ArrayList<C> getCases();
}
