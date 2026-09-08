package br.com.javaskewb.core.utils;

import br.com.javaskewb.core.Cube.State;
import br.com.javaskewb.core.Mapping.Parts.Center;
import br.com.javaskewb.core.Mapping.Parts.Corner;
import br.com.javaskewb.core.Patterns.base.Case;
import br.com.javaskewb.core.Patterns.Methods.NS.NSCases;

import java.util.ArrayList;
import java.util.List;

public class StateRank {
    private static final int [] FACTORIAL = {1, 1, 2, 6, 24, 120, 720, 5040, 40320};

    private static final int[][] CORNER_BASES = {
            {0, 1, 2}, {0, 2, 5}, {0, 5, 4}, {0, 4, 1},
            {3, 4, 5}, {3, 5, 2}, {3, 2, 1}, {3, 1, 4},
    };

    private static final int[] CORNER_BASES_INDEX = {
            -1, // 0
            -1, // 1
            -1, // 2
            0, // 3 -> {0,1,2}
            -1, // 4
            3, // 5 -> {0,4,1}
            6, // 6 -> {3,2,1}
            1, // 7 -> {0,2,5}
            7, // 8 -> {3,1,4}
            2, // 9 -> {0,5,4}
            5, // 10 -> {3,5,2}
            -1, // 11
            4  // 12 -> {3,4,5}
    };

    private static final int[] CENTER_BASE = {0, 1, 2, 3, 4, 5};
    public static final int[] CONERS_SLOT_BASE = {0, 1, 2, 3, 4, 5, 6, 7};

    public static State createState(long stateId){
        BitState bitState = new BitState(stateId);
        System.out.println("\n" + bitState + "\n");

        ArrayList<Center> centerArrayList = new ArrayList<>(6);
        ArrayList<Corner> cornerArrayList = new ArrayList<>(8);

        int[] centers = arrayReconstruct(bitState.getCentersRank(), CENTER_BASE);
        for (int i = 0; i < 6; i++)
            centerArrayList.add(new Center(i, centers[i]));


        int[] cornerIds = arrayReconstruct(
                bitState.getCornersSlotRank(),
                CONERS_SLOT_BASE
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
        List<Integer> intCenters = state.getIntCenters();
        int centersRank = calcRank(intCenters);

        int[] cornersRankings = new int[8];

        List<Corner> corners = state.getCorners();

        ArrayList<Integer> cornersId = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Corner corner = corners.get(i);
            List<Integer> faces = corner.getFaces();

            int sum = faces.get(0) + faces.get(1) +  faces.get(2);
            int cornerBasesIndex = CORNER_BASES_INDEX[sum];

            assert cornerBasesIndex != -1;

            cornersId.add(cornerBasesIndex);

            cornersRankings[i] = calcCornerRank(faces, cornerBasesIndex);
        }

        int cornersIdRank = calcRank(cornersId);


        return new BitState(centersRank, cornersIdRank, cornersRankings);
    }

    public static int calcRank(List<Integer> array){
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

    public static int calcCornerRank(List<Integer> faces, int cornerId) {
        int[] base = CORNER_BASES[cornerId];

        int rank = 0;

        for (int i = 0; i < 3; i++) {
            int position = indexOf(base, faces.get(i));

            for (int j = i + 1; j < 3; j++) {
                int otherPosition = indexOf(base, faces.get(j));

                if (otherPosition < position)
                    rank += FACTORIAL[2 - i];
            }
        }

        return rank;
    }

    private static int indexOf(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return i;
        }

        return -1;
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
        State state = State.getRandomPerspective();

        NSCases nsCases = new NSCases();
        Case nscase = nsCases.getRandomCase();

        nscase.applyCase(state);

        BitState bitState1 = StateRank.getBitState(state);

        State reconstructed = StateRank.createState(bitState1.getId());

        BitState bitState2 = StateRank.getBitState(reconstructed);

        System.out.println("IDs iguais: " +
                (bitState1.getId() == bitState2.getId()));

        System.out.println("Estados iguais: " +
                reconstructed.equals(state));
    }
}
