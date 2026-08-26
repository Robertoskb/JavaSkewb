package br.com.javaskewb.core.Patterns.utils;

import br.com.javaskewb.core.Patterns.base.Cases;

import java.util.IdentityHashMap;

public class TreeCases {
    private Cases<?> root;
    private final IdentityHashMap<Cases<?>, Cases<?>> tree = new IdentityHashMap<>();

    public TreeCases(){}

    public TreeCases(Cases<?> root){
        insertNodes(root);
        this.root = root;
    }

    public void insertNodes(Cases<?> root){
        for (Cases<?> cases: root.getSubCases()){
            tree.put(cases, root);
            insertNodes(cases);
        }
    }


    public Cases<?> getParent(Cases<?> node){
        return tree.get(node);
    }

    public boolean isRoot(Cases<?> cases){
        return root.equals(cases);
    }
}
