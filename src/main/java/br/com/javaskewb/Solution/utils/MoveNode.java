package br.com.javaskewb.Solution.utils;

public class MoveNode {
    private String currentScramble;
    private int moves;
    private int[] currentCorners = {0, 1, 2, 3, 4, 5, 6, 7};

    public MoveNode(String currentScramble, int moves) {
        setCurrentScramble(currentScramble);
        setMoves(moves);
    }

    public MoveNode(String currentScramble, int moves, int[] updateArr) {
        setCurrentScramble(currentScramble);
        setMoves(moves);
        setCurrentCorners(updateCorners(currentCorners, updateArr));
    }

    private int[] updateCorners(int[] originalCorners, int[] arr) {
        int[] corners = new int[8];

        for (int i = 0; i < 8; i++) {
            corners[i] = originalCorners[arr[i]];
        }

        return corners;
    }

    public String getCurrentScramble() {
        return currentScramble;
    }

    public void setCurrentScramble(String currentScramble) {
        this.currentScramble = currentScramble;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int[] getCurrentCorners() {
        return currentCorners;
    }

    public void setCurrentCorners(int[] currentCorners) {
        this.currentCorners = currentCorners;
    }
}
