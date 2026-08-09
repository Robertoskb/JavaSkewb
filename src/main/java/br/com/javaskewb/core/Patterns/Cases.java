package br.com.javaskewb.core.Patterns;

import java.util.ArrayList;
import java.util.Random;

public abstract class Cases<C extends Case> {
    protected static final Random random = new Random();

    public Cases(){
        fillCases();
    }

    public abstract void fillCases();

    public abstract ArrayList<C> getCases();

    public C getRandomCase(){
        ArrayList<C> cases = getCases();

        return cases.get(random.nextInt(0, cases.size()));
    }
}
