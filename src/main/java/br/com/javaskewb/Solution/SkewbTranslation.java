package br.com.javaskewb.Solution;

import br.com.javaskewb.Solution.utils.MoveNode;
import br.com.javaskewb.Solution.utils.Translate;

import java.util.*;

public class SkewbTranslation {
    private final HashMap<String, Integer> WCACorners1;
    private final HashMap<String, Integer> WCACorners2;

    private final int[] rMove = {0, 1, 2, 3, 4, 5, 6, 7};
    private final int[] lMove = {0, 1, 2, 3, 4, 5, 6, 7};
    private final int[] BMove = {0, 1, 2, 3, 4, 5, 6, 7};
    private final int[] bMove = {0, 1, 2, 3, 4, 5, 6, 7};

    private final int[] riMove = {0, 1, 2, 3, 4, 5, 6, 7};
    private final int[] liMove = {0, 1, 2, 3, 4, 5, 6, 7};
    private final int[] BiMove = {0, 1, 2, 3, 4, 5, 6, 7};
    private final int[] biMove = {0, 1, 2, 3, 4, 5, 6, 7};

    private final int[] RMove = {2, 4, 7, 3, 6, 5, 1, 0};
    private final int[] LMove = {5, 1, 0, 6, 3, 2, 4, 7};
    private final int[] FMove = {0, 3, 7, 6, 4, 2, 1, 5};
    private final int[] fMove = {7, 3, 2, 4, 1, 0, 6, 5};

    private final int[] RiMove = {7, 6, 0, 3, 1, 5, 4, 2};
    private final int[] LiMove = {2, 1, 5, 4, 6, 0, 3, 7};
    private final int[] FiMove = {0, 6, 5, 1, 4, 7, 3, 2};
    private final int[] fiMove = {5, 4, 2, 1, 3, 7, 6, 0};

    private final int[] alternativeConers = {4, 7, 6, 5, 0, 3, 2, 1};

    private final int[][] moves1 = {FMove, LMove, BMove, RMove, bMove, lMove, fMove, rMove};
    private final int[][] moves2 = {FiMove, LiMove, BiMove, RiMove, biMove, liMove, fiMove, riMove};

    private final List<Translate> translator1 = new ArrayList<>(8);
    private final List<Translate> translator2 = new ArrayList<>(8);

    public SkewbTranslation() {
        WCACorners1 = new HashMap<>(Map.of(
                "R",7, "L", 5,
                "B", 4, "U", 2
        ));

        WCACorners2 = new HashMap<>(Map.of(
                "R'",7, "L'", 5,
                "B'", 4, "U'", 2
        ));

        translator1.add(new Translate("F", "b", 0, 4)); // 0
        translator1.add(new Translate("L", "r", 1, 7)); // 1
        translator1.add(new Translate("B", "f", 2, 6)); // 2
        translator1.add(new Translate("R", "l", 3, 5)); // 3

        translator1.add(new Translate("b", "F", 4, 0)); // 4
        translator1.add(new Translate("l", "R", 5, 3)); // 5
        translator1.add(new Translate("f", "B", 6, 2)); // 6
        translator1.add(new Translate("r", "L", 7, 1)); // 7

        translator2.add(new Translate("F'", "b'", 0, 4)); // 0
        translator2.add(new Translate("L'", "r'", 1, 7)); // 1
        translator2.add(new Translate("B'", "f'", 2, 6)); // 2
        translator2.add(new Translate("R'", "l'", 3, 5)); // 3

        translator2.add(new Translate("b'", "F'", 4, 0)); // 4
        translator2.add(new Translate("l'", "R'", 5, 3)); // 5
        translator2.add(new Translate("f'", "B'", 6, 2)); // 6
        translator2.add(new Translate("r'", "L'", 7, 1)); // 7

    }

    public ArrayList<String> translate(String WCAScramble){
        ArrayList<String> scrambles = new ArrayList<>();
        List<String> scrambleBase = List.of(WCAScramble.split(" "));

        PriorityQueue<MoveNode> queue = new PriorityQueue<>(
                Comparator.comparingInt(MoveNode::getMoves)
        );

        queue.add(new MoveNode("", 0));

        while (!queue.isEmpty()){
            MoveNode node = queue.poll();

            String currentScramble = node.getCurrentScramble();
            int acm = node.getMoves();
            int[] currentCorners = node.getCurrentCorners();

            if (acm == scrambleBase.size()){
                scrambles.add(currentScramble);

                continue;
            }

            String move = scrambleBase.get(acm);

            int realCorner, alternativeCorner;

            Translate possibilities = getPossibilities(move, currentCorners);
            String possibility1, possibility2;

            possibility1 = possibilities.getReal();
            possibility2 = possibilities.getAlternative();

            realCorner = possibilities.getRealCorner();
            alternativeCorner = possibilities.getAlternativeCorner();

            int[] realArr, alternativeArr;

            int cornerFactual = getCorner(move);
            int cornerFactualAlternative = alternativeConers[cornerFactual];

            if (possibilities.isClockWise()){
                realArr = moves1[cornerFactual];
                alternativeArr = moves1[cornerFactualAlternative];
            }

            else {
                realArr = moves2[cornerFactual];
                alternativeArr = moves2[cornerFactualAlternative];
            }


            queue.add(new MoveNode(currentScramble + move + "(" + possibility1 + ") ", acm+1, realArr));
            queue.add(new MoveNode(currentScramble + move + "(*" + possibility2 + "*) ", acm+1, alternativeArr));
        }


        return scrambles;
    }

    private Translate getPossibilities(String move, int[] currentCorners){
        int corner;
        Translate possibilities;
        if (WCACorners1.containsKey(move)){
            corner = WCACorners1.get(move);
            possibilities = translator1.get(currentCorners[corner]);
        }

        else {
            corner = WCACorners2.get(move);
            possibilities = translator2.get(currentCorners[corner]);
        }

        return possibilities;
    }

    private int getCorner(String move){
        if (WCACorners1.containsKey(move)){
            return WCACorners1.get(move);

        }

        return WCACorners2.get(move);

    }

}
