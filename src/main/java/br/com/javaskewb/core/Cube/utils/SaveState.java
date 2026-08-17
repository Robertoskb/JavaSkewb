package br.com.javaskewb.core.Cube.utils;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Parts.Center;
import br.com.javaskewb.core.Mapping.Parts.Corner;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCases;

import java.util.ArrayList;
import java.util.List;

public class SaveState {
    private static final int [] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320};

    private static final int[][] CORNER_BASES = new int[][]{
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11},
            {12, 13, 14}, {15, 16, 17}, {18, 19, 20}, {21, 22, 23}
    };

    private static final int[] CENTER_BASE = {0, 1, 2, 3, 4, 5};
    public static final int[] CONERS_ID_BASE = {0, 1, 2, 3, 4, 5, 6, 7};

    public static State createState(long stateId){
        BitState bitState = new BitState(stateId);
        System.out.println("\n" + bitState + "\n");

        ArrayList<Center> centerArrayList = new ArrayList<>(6);
        ArrayList<Corner> cornerArrayList = new ArrayList<>(8);

        int[] centers = arrayReconstruct(bitState.getCentersRank(), CENTER_BASE);
        for (int i = 0; i < 6; i++)
            centerArrayList.add(new Center(i, centers[i]));


        int[] cornerIds = arrayReconstruct(
                bitState.getCornersIdRank(),
                CONERS_ID_BASE
        );

        for (int i = 0; i < 8; i++) {
            int cornerId = cornerIds[i];

            int[] faces = arrayReconstruct(
                    bitState.getCornerRank(i),
                    CORNER_BASES[cornerId]
            );

            cornerArrayList.add(new Corner(i, faces));
        }
        return new State(centerArrayList, cornerArrayList);
    }

    public static BitState getBitState(State state){
        ArrayList<Integer> intCenters = state.getIntCenters();
        int centersRank = calcRank(intCenters);

        int[] cornersRankings = new int[8];

        ArrayList<Corner> corners = state.getCorners();

        ArrayList<Integer> cornersId = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Corner corner = corners.get(i);
            ArrayList<Integer> faces = corner.getFaces();

            int min = 24;
            for (int face: faces)
                if (face < min)
                    min = face;
            cornersId.add(min/3);

            cornersRankings[i] = calcRank(faces);
        }

        int cornersIdRank = calcRank(cornersId);


        return new BitState(centersRank, cornersIdRank, cornersRankings);
    }

    public static int calcRank(ArrayList<Integer> array){
        int length = array.size();
        int rank = 0;

        for (int i = 0; i < length; i++) {
            int smallerCount = 0;
            for (int j = i + 1; j < length; j++) {
                if (array.get(j) < array.get(i))
                    smallerCount++;
            }
            rank += smallerCount * FACTORIAL[length-1-i];
        }

        return rank;
    }

    public static int[] arrayReconstruct(int rank, int[] base){
        int length = base.length;
        int[] result = new int[length];

        List<Integer> queue = new ArrayList<>();
        for (int element: base)
            queue.add(element);

        for (int i = 0; i < length; i++) {
            int fat = FACTORIAL[length-1-i];
            int index = rank / fat;
            rank = rank % fat;

            result[i] = queue.remove(index);
        }

        return result;
    }

    public static void main(String[] args) {
        State state = State.getPerspective(1);

        NSCases nsCases = new NSCases();
        Case nscase = nsCases.getRandomCase();

        nscase.applyCase(state);

        BitState bitState1 = SaveState.getBitState(state);

        State reconstructed = SaveState.createState(bitState1.getId());

        BitState bitState2 = SaveState.getBitState(reconstructed);

        System.out.println("IDs iguais: " +
                (bitState1.getId() == bitState2.getId()));

        System.out.println("Estados iguais: " +
                reconstructed.equals(state));
    }
}
