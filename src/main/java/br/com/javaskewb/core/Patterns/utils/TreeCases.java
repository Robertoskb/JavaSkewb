package br.com.javaskewb.core.Patterns.utils;

import br.com.javaskewb.core.Patterns.base.Cases;

import java.util.IdentityHashMap;

public class TreeCases {
    private final Cases<?> root;
    private final IdentityHashMap<Cases<?>, Cases<?>> tree = new IdentityHashMap<>();


    public TreeCases(Cases<?> root){
        initialize(root);
        this.root = root;
    }

    private void initialize(Cases<?> root){
        for (Cases<?> cases: root.getSubCases()){
            tree.put(cases, root);
            initialize(cases);
        }
    }

    public Cases<?> getParent(Cases<?> node){
        return tree.get(node);
    }

    public boolean isRoot(Cases<?> cases){
        return root.equals(cases);
    }
}
