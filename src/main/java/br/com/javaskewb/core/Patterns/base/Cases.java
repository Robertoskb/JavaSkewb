package br.com.javaskewb.core.Patterns.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Cases<C extends Case> {
    protected static final Random random = new Random();
    private final String name;

    public Cases(String name){
        this.name = name;
    }

    public abstract ArrayList<C> getCases();
    public List<Cases<?>> getSubCases() {
        return new ArrayList<>();
    }

    public C getRandomCase(){
        ArrayList<C> cases = getCases();

        return cases.get(random.nextInt(0, cases.size()));
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
