package br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Parts.Center;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.base.Cases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.L3C.L3CCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.L4C.L4CCases;
import br.com.javaskewb.core.Patterns.Methods.NS.L2L.LC.L5C.L5CCases;

import java.util.*;

public class LCCases extends Cases<LCCase> {
    private static final L3CCases L3CCases = new L3CCases();
    private static final L4CCases L4CCases = new L4CCases();
    private static final L5CCases L5CCases = new L5CCases();

    private static final ArrayList<Cases<?>> subCases = new ArrayList<>();

    private static final ArrayList<LCCase> cases = fill();

    public LCCases() {
        super("LC");
    }

    private static ArrayList<LCCase> fill(){
        ArrayList<LCCase> cases = new ArrayList<>();

        subCases.add(L3CCases);
        subCases.add(L4CCases);
        subCases.add(L5CCases);

        cases.addAll(L3CCases.getCases());
        cases.addAll(L4CCases.getCases());
        cases.addAll(L5CCases.getCases());

        return cases;
    }

    @Override
    public ArrayList<LCCase> getCases() {
        return cases;
    }

    @Override
    public List<Cases<?>> getSubCases() {
        return subCases;
    }

    public L3CCases getL3CCases() {
        return L3CCases;
    }

    public L4CCases getL4CCases() {
        return L4CCases;
    }

    public L5CCases getL5CCases() {
        return L5CCases;
    }

    public static void main(String[] args) {
        Cases<LCCase> cases = new LCCases();

        List<Case> variants = new ArrayList<>();

        for (Case aCase : cases.getCases()) {
            variants.addAll(aCase.getCasesVariants());
        }

        Map<String, Set<String>> mapCases = new TreeMap<>();

        List<Integer> toSee = new ArrayList<>(List.of(2, 0, 1));
        boolean getFullName = false;

        State solved = State.getSolvedState();
        for (Case aCase : variants) {
            List<Center> centers = aCase.getCaseState(solved).getCenters();

            StringBuilder stringBuilder = new StringBuilder();
            for (int i: toSee) {
                int value = centers.get(i).getValue();

                stringBuilder.append(getFaceCenterStatus(i, value));
            }

            Set<String> list = mapCases.getOrDefault(stringBuilder.toString(), new HashSet<>());
            String name;

            if (getFullName)
                name = aCase.getName();
            else
                name = Arrays.stream(aCase.getName().split(" ")).toList().getFirst();

            list.add(name);
            mapCases.put(stringBuilder.toString(), list);
        }

        for (String key : mapCases.keySet()) {
            System.out.println(key + ": " + mapCases.get(key));
        }
    }

    public static String getFaceCenterStatus(int face, int center){
        String[][] letters = {
                {"U", "A", "A", "O", "A", "A"},
                {"U", "S", "A",  "A", "A", "O"},
                {"U", "A", "S", "A", "O", "A"},

                {"O", "A", "A", "S", "A", "A"},

                {"U", "A", "O", "A", "S", "A"},
                {"U", "O", "A", "A", "A", "S"},
        };

        return  letters[face][center];
    }
}
