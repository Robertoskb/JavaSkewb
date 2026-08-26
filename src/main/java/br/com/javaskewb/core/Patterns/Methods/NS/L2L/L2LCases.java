package br.com.javaskewb.core.Patterns.Methods.NS.L2L;

import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.LCCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.CC.CCCases;

import java.util.ArrayList;
import java.util.List;

public class L2LCases extends Cases<L2LCase> {
    private static final LCCases LCCases = new LCCases();
    private static final CCCases CCCases = new CCCases();
    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    private static final ArrayList<L2LCase> cases = fill();

    public L2LCases() {
        super("L2L");
    }


    private static ArrayList<L2LCase> fill(){
        ArrayList<L2LCase> cases = new ArrayList<>();
        subCases.add(LCCases);
        subCases.add(CCCases);

        cases.addAll(LCCases.getCases());
        cases.addAll(CCCases.getCases());

        return cases;
    }

    @Override
    public ArrayList<L2LCase> getCases() {
        return cases;
    }

    @Override
    public List<Cases<?>> getSubCases() {
        return subCases;
    }

    public LCCases getLCCases() {
        return LCCases;
    }

    public CCCases getCCCCases() {
        return CCCases;
    }

    private static void printSubCases(Cases<?> cases, int tab){
        for (Cases<?> subCases: cases.getSubCases()){
            for (int i = 0; i < tab*3; i++)
                System.out.print(" ");
            System.out.println(subCases.getName());
            printSubCases(subCases, tab+1);
        }
    }

    public static void main(String[] args) {
        printSubCases(new L2LCases(), 0);
    }
}
